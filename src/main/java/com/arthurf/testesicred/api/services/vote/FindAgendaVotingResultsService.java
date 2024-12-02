package com.arthurf.testesicred.api.services.vote;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.exceptions.BusinessException;
import com.arthurf.testesicred.api.models.AgendaVotingResult;
import com.arthurf.testesicred.api.repositories.AgendaVotingResultRepository;
import com.arthurf.testesicred.api.utils.BusinessUtils;

import reactor.core.publisher.Mono;

@Service
public class FindAgendaVotingResultsService {

    @Autowired
    private AgendaVotingResultRepository agendaVotingResultRepository;

    /**
     * Find the voting results of an agenda
     * 
     * @param id The agenda identifier
     * @return The voting results
     */
    public Mono<AgendaVotingResult> execute(String id) {
        if (!BusinessUtils.isUUID(id)) {
            throw new BusinessException("Invalid agenda identifier.", HttpStatus.BAD_REQUEST);
        }

        return Mono.just(agendaVotingResultRepository.findByAgendaId(UUID.fromString(id)).orElseThrow(
                () -> new BusinessException("There's are no results for this agenda.", HttpStatus.NOT_FOUND)));
    }

}
