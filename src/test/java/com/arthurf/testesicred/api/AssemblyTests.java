package com.arthurf.testesicred.api;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.arthurf.testesicred.api.models.Member;
import com.arthurf.testesicred.api.services.member.CreateMockAssemblyService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssemblyTests {

    Logger classLogger = LoggerFactory.getLogger(AssemblyTests.class);

    @Autowired
    private CreateMockAssemblyService createMockAssemblyService;

    @Test
    public void dummy() {
    }

    @Test
    @Rollback(true)
    public void should_create_5k_members_when_no_exception() {
        final var start = new Date();

        final List<Member> result = createMockAssemblyService.execute().blockLast();

        final var end = new Date();
        classLogger.debug("Time to create 5_000 members: " + (end.getTime() - start.getTime()) + "ms");

        Assert.assertEquals(5_000, result.size());
    }
}
