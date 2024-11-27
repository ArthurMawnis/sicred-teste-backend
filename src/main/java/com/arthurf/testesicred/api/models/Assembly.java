package com.arthurf.testesicred.api.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Assembly entity class.
 * Models the cooperative assemblies.
 */
@Document(collection = "assemblies")
public class Assembly implements Serializable {

    @Id()
    private UUID id;

    @DBRef
    private Set<Agenda> agendas = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }
}
