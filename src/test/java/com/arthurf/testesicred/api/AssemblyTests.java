package com.arthurf.testesicred.api;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.arthurf.testesicred.api.models.Assembly;
import com.arthurf.testesicred.api.repositories.AssemblyRepository;
import com.arthurf.testesicred.api.services.CreateMockAssemblyService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssemblyTests {

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private CreateMockAssemblyService createMockAssemblyService;

    @Test
    public void dummy() {}

    @Test
    @Rollback(true)
    public void if_no_assembly_should_create_mock() {
        assemblyRepository.deleteAll();

        final var start = new Date();
        System.out.println();

        final Assembly result = createMockAssemblyService.execute().block();

        final var end = new Date();
        System.out.println("Time to create 5_000 members: " + (end.getTime() - start.getTime()) + "ms");
        System.out.println("Assembly created: " + result.getId());
    }
}
