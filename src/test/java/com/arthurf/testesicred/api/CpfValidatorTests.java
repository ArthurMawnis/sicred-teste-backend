package com.arthurf.testesicred.api;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.arthurf.testesicred.api.clients.CpfApiClient;
import com.arthurf.testesicred.api.dtos.ValidateCpfResponseDTO;
import com.arthurf.testesicred.api.models.enums.ValidateCpfStatusEnum;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CpfValidatorTests {

    @Autowired
    private CpfApiClient cpfApiClient;

    @Test
    public void dummy() {
    }

    @Test
    public void should_404_when_cpf_is_invalid() {
        try {
            cpfApiClient.checkCpf("souinvalidoooo");
            Assertions.fail("Should return 404");
        } catch (WebClientResponseException e) {
            Assertions.assertEquals(HttpStatusCode.valueOf(404), e.getStatusCode());
        }
    }

    @Test
    public void should_200_when_cpf_is_valid() {
        final String validCpf = "41516080106";
        final String invalidCpf = "82378593163";

        final ValidateCpfResponseDTO responseForValid = cpfApiClient.checkCpf(validCpf);
        Assertions.assertEquals(ValidateCpfStatusEnum.ABLE_TO_VOTE, responseForValid.getStatus());

        final ValidateCpfResponseDTO responseForInvalid = cpfApiClient.checkCpf(invalidCpf);
        Assertions.assertEquals(ValidateCpfStatusEnum.UNABLE_TO_VOTE, responseForInvalid.getStatus());
    }

}
