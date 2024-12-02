package com.arthurf.testesicred.api.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arthurf.testesicred.api.configs.CacheConfig;
import com.arthurf.testesicred.api.dtos.ValidateCpfResponseDTO;

import jakarta.annotation.PostConstruct;

@Service
public class CpfApiClient {

    @Value("${cpf-api.url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
    }

    /**
     * Method to check if a CPF is valid
     * 
     * @param cpf
     * @return
     */
    @Cacheable(CacheConfig.CACHE_CPF_VALIDATOR)
    public ValidateCpfResponseDTO checkCpf(final String cpf) {
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(baseUrl + cpf, ValidateCpfResponseDTO.class);
    }
}
