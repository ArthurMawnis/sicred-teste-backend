package com.arthurf.testesicred.api.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.arthurf.testesicred.api.dtos.CreateVotingSessionResultDTO;
import com.arthurf.testesicred.api.services.vote.SaveAgendaVotesResultService;
import com.google.gson.Gson;

@Component
public class PublishVotingSessionResultListener {

    @Value("${rabbitmq.queue}")
    String queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Autowired
    private SaveAgendaVotesResultService saveAgendaVotesResultService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        saveAgendaVotesResultService.execute(new Gson().fromJson(message,
                CreateVotingSessionResultDTO.class));

    }

}
