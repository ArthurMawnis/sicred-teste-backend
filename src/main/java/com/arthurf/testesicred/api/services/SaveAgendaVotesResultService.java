package com.arthurf.testesicred.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionResultDTO;
import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.models.AgendaVotingResult;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.repositories.AgendaRepository;
import com.arthurf.testesicred.api.repositories.AgendaVotingResultRepository;
import com.arthurf.testesicred.api.repositories.VoteRepository;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;

@Service
public class SaveAgendaVotesResultService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AgendaVotingResultRepository agendaVotingResultRepository;

    public void execute(CreateVotingSessionResultDTO createVotingSessionResultDTO) {
        try {
            final VotingSession votingSession = votingSessionRepository
                    .findById(UUID.fromString(createVotingSessionResultDTO.getSessionId()))
                    .orElseThrow(
                            () -> new BusinessException("Voting not longer exists", HttpStatus.PRECONDITION_FAILED));

            final Agenda agenda = agendaRepository.findById(UUID.fromString(votingSession.getAgendaId()))
                    .orElseThrow(
                            () -> new BusinessException("Agenda not longer exists", HttpStatus.PRECONDITION_FAILED));

            final Long positiveVotes = voteRepository.countByIdAgendaIdAndIsPositive(agenda.getId(), true);
            final Long negativeVotes = voteRepository.countByIdAgendaIdAndIsPositive(agenda.getId(), false);

            final AgendaVotingResult result = new AgendaVotingResult();
            result.setId(UUID.randomUUID());
            result.setAgendaId(agenda.getId());
            result.setYesVotes(positiveVotes);
            result.setNoVotes(negativeVotes);

            agendaVotingResultRepository.save(result);
        } catch (BusinessException ignorable) {
            System.out.println(ignorable.getMessage());
            // Do nothing
        }

    }

}
