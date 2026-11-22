package com.opspilot.messaging;

import com.opspilot.config.MessagingConfig;
import com.opspilot.entity.EnvironmentRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentRequestProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEnvironmentRequest(EnvironmentRequest request) {
        // We send the ID of the request so the consumer can fetch it from the DB
        rabbitTemplate.convertAndSend(MessagingConfig.ENVIRONMENT_REQUEST_QUEUE, request.getId().toString());
        System.out.println("Sent environment request to RabbitMQ: " + request.getId());
    }
}