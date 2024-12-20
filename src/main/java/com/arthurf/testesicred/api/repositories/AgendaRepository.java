package com.arthurf.testesicred.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arthurf.testesicred.api.models.Agenda;

@Repository
public interface AgendaRepository extends MongoRepository<Agenda, UUID> {

    /**
     * Find an agenda by subject.
     * 
     * @param subject the subject.
     * @return the agenda.
     */
    Optional<Agenda> findBySubject(final String subject);

}
