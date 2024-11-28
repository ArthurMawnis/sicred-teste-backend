package com.arthurf.testesicred.api.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Assembly;
import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.repositories.AssemblyRepository;
import com.arthurf.testesicred.api.repositories.MemberRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Mono;

/**
 * Service to create a mock assembly.
 */
@Service
public class CreateMockAssemblyService {

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Creates a mock assembly with 5k members.
     * 
     * @return the created assembly.
     */
    public Mono<Assembly> execute() {
        final int membersCount = 5_000;
        final var assemblies = assemblyRepository.findAll();

        if (assemblies.isEmpty()) {
            final var assembly = new Assembly();
            assembly.setId(UUID.randomUUID());
            List<Member> members = new ArrayList<>();

            for (int i = 0; i < membersCount; i++) {
                final var member = new Member();
                member.setId(UUID.randomUUID());
                member.setCpf(BusinessUtils.generateRandomCPF());
                members.add(member);
            }

            memberRepository.saveAll(members);
            assembly.setMembers(new HashSet<>(members));

            assemblyRepository.save(assembly);
            return Mono.just(assembly);
        } else {
            throw new BusinessException("There is already an assembly in the database", HttpStatus.PRECONDITION_FAILED);
        }
    }
}
