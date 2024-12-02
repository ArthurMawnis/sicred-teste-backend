package com.arthurf.testesicred.api.services.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.repositories.AgendaRepository;

import reactor.core.publisher.Flux;

@Service
public class FindAllAgendasService {

    @Autowired
    private AgendaRepository agendaRepository;

    /**
     * Find all agendas.
     * 
     * @return a Flux containing all agendas.
     */
    public Flux<Agenda> execute() {
        return Flux.fromIterable(agendaRepository.findAll());
    }
}
