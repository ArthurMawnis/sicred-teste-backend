package com.arthurf.testesicred.api.services.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.arthurf.testesicred.api.dtos.CreateAgendaDTO;
import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.Agenda;
import com.arthurf.testesicred.api.repositories.AgendaRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Mono;

/**
 * CreateAgendaService class.
 */
@Service
public class CreateAgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    /**
     * Creates a new agenda.
     * Rules: 
     * - The subject is required. 
     * - The description is required. 
     * - The subject must be unique.
     * 
     * @param createAgendaDTO the CreateAgendaDTO containg the agenda subject and
     *                        description.
     * @return the newly created agenda.
     */
    public Mono<Agenda> execute(final CreateAgendaDTO createAgendaDTO) {
        final var errors = validate(createAgendaDTO);

        if (!errors.isEmpty()) {
            throw new BusinessException(BusinessUtils.parseErrorsList(errors), HttpStatus.BAD_REQUEST);
        }

        final var agenda = new Agenda();
        agenda.setSubject(createAgendaDTO.getSubject());
        agenda.setDescription(createAgendaDTO.getDescription());
        agenda.setId(UUID.randomUUID());

        return Mono.just(agendaRepository.save(agenda));
    }

    /**
     * Validate the CreateAgendaDTO.
     * 
     * @param createAgendaDTO the CreateAgendaDTO.
     * @return the list of errors.
     */
    private List<String> validate(final CreateAgendaDTO createAgendaDTO) {
        final List<String> errors = new ArrayList<>();

        if (ObjectUtils.isEmpty(createAgendaDTO)) {
            errors.add("The request body is required.");
            return errors;
        }

        if (ObjectUtils.isEmpty(createAgendaDTO.getSubject())) {
            errors.add("The subject is required.");
        }

        if (ObjectUtils.isEmpty(createAgendaDTO.getDescription())) {
            errors.add("The description is required.");
        }

        if (!errors.isEmpty()) {
            return errors;
        }

        final var agendaOpt = agendaRepository.findBySubject(createAgendaDTO.getSubject());

        if (agendaOpt.isPresent()) {
            errors.add("The subject is already in use.");
        }

        return errors;
    }

}
