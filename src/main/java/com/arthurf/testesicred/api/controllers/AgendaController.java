package com.arthurf.testesicred.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthurf.testesicred.api.dtos.CreateAgendaDTO;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.models.AgendaVotingResult;
import com.arthurf.testesicred.api.services.CreateAgendaService;
import com.arthurf.testesicred.api.services.FindAgendaVotingResultsService;
import com.arthurf.testesicred.api.services.FindAllAgendasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller class for Agenda.
 */
@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private CreateAgendaService createAgendaService;

    @Autowired
    private FindAllAgendasService findAllAgendas;

    @Autowired
    private FindAgendaVotingResultsService findAgendaVotingResultsService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an agenda.", description = "Create an agenda.")
    public Mono<Agenda> createAgenda(@RequestBody() final CreateAgendaDTO createAgendaDTO) {
        return createAgendaService.execute(createAgendaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all agendas.", description = "Retrieve a list of all agendas.")
    public Flux<Agenda> getAllAgendas() {
        return findAllAgendas.execute();
    }

    @GetMapping("/{id}/results")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get agenda results.", description = "Retrieve the results of a specific agenda.")
    public Mono<AgendaVotingResult> getAgendaResults(
            @PathVariable("id") @Schema(description = "The agenda identifier") String id) {
        return findAgendaVotingResultsService.execute(id);
    }
}
