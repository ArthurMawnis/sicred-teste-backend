package com.arthurf.testesicred.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.AgendaVotingResult;

/**
 * Repository for {@link AgendaVotingResult} entity
 */
@Repository
public interface AgendaVotingResultRepository extends MongoRepository<AgendaVotingResult, UUID> {

    Optional<AgendaVotingResult> findByAgendaId(UUID fromString);

}
