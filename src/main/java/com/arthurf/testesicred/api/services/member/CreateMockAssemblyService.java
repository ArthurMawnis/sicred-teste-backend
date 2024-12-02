package com.arthurf.testesicred.api.services.member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.repositories.MemberRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Flux;

/**
 * Service to create a mock assembly.
 */
@Service
public class CreateMockAssemblyService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Creates a mock assembly with 5k members.
     * 
     * @return the created assembly.
     */
    public Flux<List<Member>> execute() {
        final int membersCount = 5_000;
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < membersCount; i++) {
            final var member = new Member();
            member.setId(UUID.randomUUID());
            member.setCpf(BusinessUtils.generateRandomCPF());
            members.add(member);
        }

        return Flux.just(memberRepository.saveAll(members));
    }
}
