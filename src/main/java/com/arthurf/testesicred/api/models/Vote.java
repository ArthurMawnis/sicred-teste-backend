package com.arthurf.testesicred.api.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.arthurf.testesicred.api.models.pks.VotePk;

/**
 * Vote
 * 
 * Model class for Vote
 */
@Document(collection = "votes")
public class Vote {

    @Id()
    private VotePk id;
    @Version()
    private Long version;
    private Boolean isPositive;
    @CreatedDate
    private LocalDateTime createdAt;

    public VotePk getId() {
        return id;
    }

    public void setId(VotePk id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Boolean isPositive() {
        return isPositive;
    }

    public void setPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
