package com.arthurf.testesicred.api.services.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.dtos.CreateMemberDTO;
import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.repositories.MemberRepository;

import reactor.core.publisher.Mono;

/**
 * Service to create a member.
 */
@Service
public class CreateMemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Executes the service.
     * 
     * @param createMemberDTO The DTO with the data to create the member.
     * @return The created member.
     */
    public Mono<Member> execute(final CreateMemberDTO createMemberDTO) {
        final Optional<Member> alreadyExistingMember = memberRepository.findByCpf(createMemberDTO.getCpf());

        if (alreadyExistingMember.isPresent()) {
            throw new BusinessException("A member with this cpf is already registered.",
                    HttpStatus.CONFLICT);
        }

        final Member member = new Member();
        member.setCpf(createMemberDTO.getCpf());
        member.setId(UUID.randomUUID());

        return Mono.just(memberRepository.save(member));
    }

}
