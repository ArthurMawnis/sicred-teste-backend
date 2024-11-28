package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating an agenda.
 */
@Schema(name = "CreateAgendaDTO", description = "Data Transfer Object for creating an agenda.")
public class CreateAgendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "The subject of the agenda.", example = "Should we increase the price of the product?")
    @NotNull(message = "The subject of the agenda is required.")
    private String subject;
    @Schema(description = "The description of the agenda.", example = "The product is not selling well and we need to increase the price to keep the company profitable.")
    @NotNull(message = "The description of the agenda is required.")
    private String description;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
