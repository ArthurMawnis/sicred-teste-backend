package com.arthurf.testesicred.api.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for creating a member.
 */
@Schema(name = "CreateMemberDTO", description = "Data Transfer Object for creating a member.")
public class CreateMemberDTO implements Serializable {

    @Schema(description = "The CPF credential of the member.", example = "12345678901")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
