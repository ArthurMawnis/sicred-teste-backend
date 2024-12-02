package com.arthurf.testesicred.api.services.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionResultDTO;
import com.google.gson.Gson;

/**
 * Service for publishing a message to the RabbitMQ server.
 */
@Service
public class PublishVotingSessionResultService {

    private final AmqpTemplate amqpTemplate;

    Logger classLogger = LoggerFactory.getLogger(PublishVotingSessionResultService.class);

    @Value("${rabbitmq.queue}")
    private String queue;

    /**
     * Constructor.
     * 
     * @param amqpTemplate The RabbitMQ template.
     */
    public PublishVotingSessionResultService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * Publishes a message to the RabbitMQ server.
     * 
     * @param message The message to be published.
     */
    public void publishMessage(final CreateVotingSessionResultDTO message) {
        try {
            amqpTemplate.convertAndSend(queue, new Gson().toJson(message));
        } catch (Exception e) {
            classLogger.error(e.getMessage(), e);
        }

    }
}
