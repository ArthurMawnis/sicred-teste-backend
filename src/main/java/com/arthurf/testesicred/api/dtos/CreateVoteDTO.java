package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a vote.
 */
@Schema(name = "CreateVoteDTO", description = "Data Transfer Object for creating a vote.")
public class CreateVoteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The member id that is voting.", example = "abc12345678901")
    @NotNull(message = "The member id that is voting is required.")
    private String memberId;
    @Schema(description = "The vote value. true for 'Sim' and false for 'Não'", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean voteValue;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Boolean getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Boolean voteValue) {
        this.voteValue = voteValue;
    }
}
