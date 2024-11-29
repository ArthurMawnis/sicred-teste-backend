package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a voting session result.
 */
@Schema(name = "CreateVotingSessionResultDTO", description = "Data Transfer Object for creating a voting session result.")
public class CreateVotingSessionResultDTO implements Serializable {

    private String sessionId;

    /**
     * Default constructor.
     */
    public CreateVotingSessionResultDTO() {
    }

    /**
     * Constructor.
     * 
     * @param sessionId The identifier of the voting session to be closed.
     */
    public CreateVotingSessionResultDTO(final String sessionId) {
        this.sessionId = sessionId;
    }

    @Schema(description = "The identifier of the voting session to be closed.")
    @NotNull(message = "The identifier of the voting session to be closed.")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
