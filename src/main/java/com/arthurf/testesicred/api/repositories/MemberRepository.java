package com.arthurf.testesicred.api.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.Member;

import reactor.core.publisher.Mono;

/**
 * Repository for the members.
 */
@Repository
public interface MemberRepository extends MongoRepository<Member, UUID> {

    /**
     * Find a member by its CPF.
     * 
     * @param cpf The CPF of the member.
     * @return The member found.
     */
    Mono<Member> findByCpf(String cpf);
}
