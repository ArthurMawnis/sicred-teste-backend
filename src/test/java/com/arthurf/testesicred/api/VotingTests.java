package com.arthurf.testesicred.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionResultDTO;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.models.AgendaVotingResult;
import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.models.Vote;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.models.enums.VotingSessionStatusEnum;
import com.arthurf.testesicred.api.models.pks.VotePk;
import com.arthurf.testesicred.api.repositories.AgendaRepository;
import com.arthurf.testesicred.api.repositories.AgendaVotingResultRepository;
import com.arthurf.testesicred.api.repositories.MemberRepository;
import com.arthurf.testesicred.api.repositories.VoteRepository;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;
import com.arthurf.testesicred.api.services.vote.SaveAgendaVotesResultService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VotingTests {

    @Autowired
    private SaveAgendaVotesResultService saveAgendaVotesResultService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AgendaVotingResultRepository agendaVotingResultRepository;

    @Test
    public void dummy() {
    }

    @Test
    @Rollback(true)
    public void should_save_agenda_votes_result_with_correct_votes_count() {
        // Arrange
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final var member = new Member();
            member.setId(UUID.randomUUID());
            member.setCpf("0000000000" + i);
            members.add(member);
        }

        members = memberRepository.saveAll(members);

        Agenda agenda = new Agenda();
        agenda.setId(UUID.randomUUID());
        agenda.setSubject("Test Agenda");
        agenda.setDescription("Description of a testing agenda mock");

        agenda = agendaRepository.save(agenda);

        VotingSession votingSession = new VotingSession();
        votingSession.setId(UUID.randomUUID());
        votingSession.setAgendaId(agenda.getId().toString());
        votingSession.setStartedAt(LocalDateTime.now().minusMinutes(10));
        votingSession.setDuration(60L);
        votingSession.setStatus(VotingSessionStatusEnum.CLOSED);

        votingSession = votingSessionRepository.save(votingSession);

        final List<Vote> votes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final var vote = new Vote();
            vote.setId(new VotePk(agenda.getId().toString(), members.get(i).getId().toString()));
            vote.setPositive(i % 2 == 0);
            votes.add(vote);
        }

        voteRepository.saveAll(votes);

        final CreateVotingSessionResultDTO createVotingSessionResultDTO = new CreateVotingSessionResultDTO();
        createVotingSessionResultDTO.setSessionId(votingSession.getId().toString());

        // Act
        saveAgendaVotesResultService.execute(createVotingSessionResultDTO);

        // Assert
        // No exception should be thrown
        final AgendaVotingResult result = agendaVotingResultRepository.findByAgendaId(agenda.getId()).orElseThrow(
                () -> new RuntimeException("Agenda voting result not found"));

        assert result.getYesVotes() == 5;
        assert result.getNoVotes() == 5;
    }

}
