package com.arthurf.testesicred.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.models.enums.VotingSessionStatusEnum;

@Repository
public interface VotingSessionRepository extends MongoRepository<VotingSession, UUID> {

    /**
     * Find a voting session by the agenda id and status
     * 
     * @param agenda The agenda of the voting session
     * @param status The status of the voting session
     * @return The voting session
     */
    List<VotingSession> findByAgendaIdAndStatus(final String agendaId, final VotingSessionStatusEnum status);

}
