package com.arthurf.testesicred.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.arthurf.testesicred.api.models.pks.VotePk;

@Document(collection = "votes")
public class Vote {

    @Id()
    private VotePk id;
    private Boolean isPositive;

    public VotePk getId() {
        return id;
    }

    public void setId(VotePk id) {
        this.id = id;
    }

    public Boolean isPositive() {
        return isPositive;
    }

    public void setPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }
}
