package com.arthurf.testesicred.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents the voting result of an agenda
 */
@Document(collection = "agenda_voting_results")
public class AgendaVotingResult {

    @Id()
    private UUID id;
    @Version()
    private Long version;

    private UUID agendaId;
    private Long yesVotes;
    private Long noVotes;
    @CreatedDate
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UUID getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(UUID agendaId) {
        this.agendaId = agendaId;
    }

    public Long getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(Long yesVotes) {
        this.yesVotes = yesVotes;
    }

    public Long getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(Long noVotes) {
        this.noVotes = noVotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
