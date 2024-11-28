package com.arthurf.testesicred.api.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.arthurf.testesicred.api.models.enums.VotingSessionStatusEnum;

/**
 * VotingSession
 * 
 * Model class for VotingSession entity
 */
@Document(collection = "voting_sessions")
public class VotingSession {

    @Id()
    private UUID id;

    private String agendaId;
    private Long duration;
    private VotingSessionStatusEnum status;

    /**
     * Checks if the voting session is open.
     * 
     * @return true if the voting session is open, false otherwise.
     */
    public boolean isOpen() {
        return VotingSessionStatusEnum.OPEN.equals(status);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public VotingSessionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VotingSessionStatusEnum status) {
        this.status = status;
    }
}
