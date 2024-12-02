package com.arthurf.testesicred.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionResultDTO;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.models.enums.VotingSessionStatusEnum;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;

@Service
public class CloseVotingSessionJobService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private PublishVotingSessionResultService publishVotingSessionResultService;

    @Scheduled(cron = "*/10 * * * * *")
    public void checkVotingSession() {
        // Supposing this list to be small, we can filter the open sessions and then
        // filter the expired ones
        final List<VotingSession> openSessions = votingSessionRepository.findAllByStatus(VotingSessionStatusEnum.OPEN);
        final List<VotingSession> expiredSessions = openSessions.stream().filter(VotingSession::isExpired).toList();

        for (final VotingSession votingSession : expiredSessions) {
            final CreateVotingSessionResultDTO votingSessionResult = new CreateVotingSessionResultDTO(
                    votingSession.getId().toString());

            publishVotingSessionResultService.publishMessage(votingSessionResult);

            votingSession.setStatus(VotingSessionStatusEnum.CLOSED);
            votingSessionRepository.save(votingSession);
        }
    }
}
