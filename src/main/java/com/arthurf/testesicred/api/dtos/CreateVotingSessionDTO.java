package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

@Schema(name = "CreateVotingSessionDTO", description = "Data Transfer Object for creating a voting session.")
public class CreateVotingSessionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The id of the agenda that the voting session is related to.", example = "60f3b3b3b3b3b3b3b3b3b3b3")
    @NotNull(message = "The id of the agenda is required.")
    private String agendaId;

    @Schema(description = "The duration of the voting session in milliseconds.", example = "60000")
    @Nullable
    private Long duration;

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

}
