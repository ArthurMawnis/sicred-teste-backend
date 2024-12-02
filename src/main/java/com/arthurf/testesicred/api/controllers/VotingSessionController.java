package com.arthurf.testesicred.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthurf.testesicred.api.dtos.CreateVoteDTO;
import com.arthurf.testesicred.api.dtos.CreateVotingSessionDTO;
import com.arthurf.testesicred.api.models.VotingSession;
import com.arthurf.testesicred.api.services.vote.CreateVoteService;
import com.arthurf.testesicred.api.services.vote.CreateVotingSessionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Mono;

/**
 * Controller class for VotingSession.
 */
@RestController
@RequestMapping("/voting-sessions")
public class VotingSessionController {

    @Autowired
    private CreateVotingSessionService votingSessionService;

    @Autowired
    private CreateVoteService createVoteService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create and open a voting session.", description = "Create and open a voting session for a specific agenda.")
    public Mono<VotingSession> createAndOpenVotingSession(
            @RequestBody() final CreateVotingSessionDTO createVotingSessionDTO) {
        return votingSessionService.execute(createVotingSessionDTO);
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Vote in a voting session.", description = "Vote in a voting session for a specific agenda.")
    public Mono<Void> doVote(
            @PathVariable("id") @Schema(name = "id", description = "The voting session id") final String votingSessionId,
            @RequestBody() final CreateVoteDTO createVoteDTO) {
        createVoteService.execute(votingSessionId, createVoteDTO);
        return Mono.empty();
    }
}
