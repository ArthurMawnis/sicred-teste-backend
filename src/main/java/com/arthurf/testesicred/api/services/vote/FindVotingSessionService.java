package com.arthurf.testesicred.api.services.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.repositories.VotingSessionRepository;

import reactor.core.publisher.Flux;

/**
 * FindVotingSessionService
 * 
 * Service class for finding
 */
@Service
public class FindVotingSessionService {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    /**
     * Find a voting session by the agenda id
     * 
     * @param agendaId The agenda id
     * @return The voting session
     */
    public Flux<VotingSession> execute(String agendaId) {
        return Flux.fromIterable(votingSessionRepository.findAllByAgendaId(agendaId));

    }

}
