package com.arthurf.testesicred.api.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.arthurf.testesicred.api.configs.CacheConfig;
import com.arthurf.testesicred.api.dtos.ValidateCpfResponseDTO;

import jakarta.annotation.PostConstruct;

@Service
public class CpfApiClient {

    @Value("${cpf-api.url}")
    private String baseUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    /**
     * Method to check if a CPF is valid
     * 
     * @param cpf
     * @return
     */
    @Cacheable(CacheConfig.CACHE_CPF_VALIDATOR)
    public ValidateCpfResponseDTO checkCpf(final String cpf) {
        return webClient.get()
                .uri("/" + cpf)
                .retrieve()
                .bodyToMono(ValidateCpfResponseDTO.class)
                .block();
    }
}
