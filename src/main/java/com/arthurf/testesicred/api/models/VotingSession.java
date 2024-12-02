package com.arthurf.testesicred.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
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
    @Version()
    private Long version;
    private String agendaId;
    private Long duration;
    private VotingSessionStatusEnum status;
    private LocalDateTime startedAt;
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Checks if the voting session is open.
     * 
     * @return true if the voting session is open, false otherwise.
     */
    public boolean isOpen() {
        return VotingSessionStatusEnum.OPEN.equals(status);
    }

    /**
     * Checks if the voting session is expired.
     * 
     * @return true if the voting session is expired
     */
    public boolean isExpired() {
        if (startedAt == null) {
            return false;
        }

        return LocalDateTime.now().isAfter(startedAt.plusSeconds(duration));
    }

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

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
