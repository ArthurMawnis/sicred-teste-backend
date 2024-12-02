package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import com.arthurf.testesicred.api.models.enums.ValidateCpfStatusEnum;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object to represent the response of the CPF validation
 */
@Schema(name = "ValidateCpfResponse", description = "Response of the CPF validation")
public class ValidateCpfResponseDTO implements Serializable {

    /**
     * Status of the CPF validation
     */
    @Schema(description = "Status of the CPF validation")
    private ValidateCpfStatusEnum status;

    public ValidateCpfStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ValidateCpfStatusEnum status) {
        this.status = status;
    }
}
