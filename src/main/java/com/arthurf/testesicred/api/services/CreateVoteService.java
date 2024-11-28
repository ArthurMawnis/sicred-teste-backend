package com.arthurf.testesicred.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.arthurf.testesicred.api.dtos.CreateVoteDTO;
import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Vote;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.models.pks.VotePk;
import com.arthurf.testesicred.api.repositories.MemberRepository;
import com.arthurf.testesicred.api.repositories.VoteRepository;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Mono;

/**
 * Service class for creating a vote.
 */
@Service
public class CreateVoteService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private VoteRepository voteRepository;

    /**
     * Executes the service.
     * Rules:
     * - The member must exist.
     * - The voting session must exist.
     * - The voting session must be open.
     * - The member must not have voted yet.
     * 
     * @param votingSessionId The voting session id.
     * @param createVoteDTO   The DTO with the vote data.
     * @return A Mono with the result of the operation.
     */
    public Mono<Void> execute(
            final String votingSessionId,
            final CreateVoteDTO createVoteDTO) {

        final List<String> errors = validate(votingSessionId, createVoteDTO);

        if (!errors.isEmpty()) {
            throw new BusinessException(BusinessUtils.parseErrorsList(errors), HttpStatus.BAD_REQUEST);
        }

        final boolean memberExists = memberRepository.existsById(UUID.fromString(createVoteDTO.getMemberId()));

        if (!memberExists) {
            throw new BusinessException("The member does not exist.", HttpStatus.NOT_FOUND);
        }

        final VotingSession votingSession = findVotingSession(votingSessionId);

        if (!votingSession.isOpen()) {
            throw new BusinessException("The voting session is closed.", HttpStatus.PRECONDITION_FAILED);
        }

        final boolean alreadyVoted = verifyIfalreadyVoted(createVoteDTO.getMemberId(), votingSession.getAgendaId());

        if (alreadyVoted) {
            throw new BusinessException("The member has already voted for this voting session.",
                    HttpStatus.PRECONDITION_FAILED);
        }

        vote(votingSession.getAgendaId(), createVoteDTO.getMemberId(), createVoteDTO.getVoteValue());

        return Mono.empty();
    }

    /**
     * Votes for a agenda, creating a {@link Vote} entity.
     * 
     * @param agendaId  The voting session's agenda id.
     * @param memberId  The member id.
     * @param voteValue The vote value.
     */
    private void vote(final String agendaId, final String memberId, final boolean voteValue) {
        final Vote vote = new Vote();
        final VotePk votePk = new VotePk();

        votePk.setMemberId(memberId);
        votePk.setAgendaId(agendaId);
        vote.setPositive(voteValue);
        vote.setId(votePk);

        voteRepository.save(vote);
    }

    private VotingSession findVotingSession(final String votingSessionId) {
        return votingSessionRepository.findById(UUID.fromString(votingSessionId)).orElseThrow(
                () -> new BusinessException("The voting session does not exist.", HttpStatus.NOT_FOUND));
    }

    /**
     * Validates the voting rule: A member cannot vote twice for a agenda.
     * 
     * @param agendaId      The voting session's agenda id.
     * @param createVoteDTO The DTO with the vote data.
     * @return A list with the errors found.
     */
    private boolean verifyIfalreadyVoted(final String memberId, final String agendaId) {
        return voteRepository.findById(new VotePk(agendaId, memberId)).isPresent();
    }

    /**
     * Validates the vote data.
     * 
     * @param votingSessionId The voting session id.
     * @param createVoteDTO   The DTO with the vote data.
     * @return A list with the errors found.
     */
    private List<String> validate(
            final String votingSessionId,
            final CreateVoteDTO createVoteDTO) {

        final List<String> errors = new ArrayList<>();

        if (ObjectUtils.isEmpty(votingSessionId)) {
            errors.add("The voting session id is required.");
        }

        if (ObjectUtils.isEmpty(createVoteDTO)) {
            errors.add("The vote data is required.");
        } else {
            if (ObjectUtils.isEmpty(createVoteDTO.getMemberId())) {
                errors.add("The member id is required.");
            }
            if (ObjectUtils.isEmpty(createVoteDTO.getVoteValue())) {
                errors.add("The vote value is required.");
            }
        }

        return errors;
    }
}
