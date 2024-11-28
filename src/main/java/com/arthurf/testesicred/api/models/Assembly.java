package com.arthurf.testesicred.api.models;

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
public class Assembly {

    @Id()
    private UUID id;

    @DBRef
    private Set<Agenda> agendas = new HashSet<>();

    @DBRef
    private Set<Member> members = new HashSet<>();

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

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
