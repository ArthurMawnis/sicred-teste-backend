package com.arthurf.testesicred.api.services.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.repositories.MemberRepository;

import reactor.core.publisher.Flux;

/**
 * Service class for finding all members.
 */
@Service
public class FindMembersService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Find all members.
     * 
     * @return a Flux containing all members.
     */
    public Flux<Member> execute(final int page) {
        final Pageable pageable = Pageable.ofSize(20).withPage(page);
        return Flux.fromIterable(memberRepository.findAll(pageable));
    }
}
