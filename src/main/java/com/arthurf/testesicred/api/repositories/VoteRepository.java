package com.arthurf.testesicred.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.Vote;
import com.arthurf.testesicred.api.models.pks.VotePk;

@Repository
public interface VoteRepository extends MongoRepository<Vote, VotePk> {

    @Query(value = "{ 'id.agendaId' : ?0, 'isPositive' : ?1 }", count = true)
    Long countByIdAgendaIdAndIsPositive(final String id, boolean b);
}
