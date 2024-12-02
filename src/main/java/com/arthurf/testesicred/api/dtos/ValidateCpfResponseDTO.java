package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import com.arthurf.testesicred.api.models.enums.ValidateCpfStatusEnum;

/**
 * Data Transfer Object to represent the response of the CPF validation
 */
public class ValidateCpfResponseDTO implements Serializable {

    /**
     * Status of the CPF validation
     */
    private ValidateCpfStatusEnum status;

    public ValidateCpfStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ValidateCpfStatusEnum status) {
        this.status = status;
    }
}
