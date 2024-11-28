package com.arthurf.testesicred.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthurf.testesicred.api.models.Assembly;
import com.arthurf.testesicred.api.services.CreateMockAssemblyService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assemblies")
public class AssemblyController {

    @Autowired
    private CreateMockAssemblyService createMockAssemblyService;

    @PostMapping("mock-data")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Assembly> createMockAssembly() {
        return createMockAssemblyService.execute();
    }

}
