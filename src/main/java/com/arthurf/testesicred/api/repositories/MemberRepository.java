package com.arthurf.testesicred.api.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, UUID> {
}
