package com.arthurf.testesicred.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.Vote;
import com.arthurf.testesicred.api.models.pks.VotePk;

@Repository
public interface VoteRepository extends MongoRepository<Vote, VotePk> {}
