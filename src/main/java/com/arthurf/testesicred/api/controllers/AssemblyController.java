package com.arthurf.testesicred.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthurf.testesicred.api.dtos.CreateMemberDTO;
import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.services.member.CreateMemberService;
import com.arthurf.testesicred.api.services.member.CreateMockAssemblyService;
import com.arthurf.testesicred.api.services.member.FindMembersService;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assemblies")
public class AssemblyController {

    @Autowired
    private CreateMockAssemblyService createMockAssemblyService;

    @Autowired
    private FindMembersService findMembersService;

    @Autowired
    private CreateMemberService createMemberService;

    @PostMapping("mock-data")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a mock assembly.", description = "Create a mock assembly.")
    public Flux<List<Member>> createMockAssembly() {
        return createMockAssemblyService.execute();
    }

    @Operation(summary = "Find all assembly members.", description = "Retrieve a list of all assembly's members.")
    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Member> findAllMembers(
            @RequestParam(name = "page", defaultValue = "0") final int page) {
        return findMembersService.execute(page);
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new member.", description = "Add a new member to the assembly.")
    public Mono<Member> addMember(@RequestBody() CreateMemberDTO createMemberDTO) {
        return createMemberService.execute(createMemberDTO);
    }

}
