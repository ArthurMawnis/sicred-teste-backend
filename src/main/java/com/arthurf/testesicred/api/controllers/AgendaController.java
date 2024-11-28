package com.arthurf.testesicred.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthurf.testesicred.api.dtos.CreateAgendaDTO;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.services.CreateAgendaService;
import com.arthurf.testesicred.api.services.FindAllAgendas;

import io.swagger.v3.oas.annotations.Operation;
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
    private FindAllAgendas findAllAgendas;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an agenda.", description = "Create an agenda.")
    public Mono<Agenda> createAgenda(@RequestBody() final CreateAgendaDTO createAgendaDTO) {
        return createAgendaService.execute(createAgendaDTO);
    }

    @GetMapping
    @Operation(summary = "Get all agendas.", description = "Retrieve a list of all agendas.")
    public Flux<Agenda> getAllAgendas() {
        return findAllAgendas.execute();
    }
}
