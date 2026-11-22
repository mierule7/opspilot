package com.opspilot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String ENVIRONMENT_REQUEST_QUEUE = "environment-request-queue";
    public static final String ENVIRONMENT_EVENTS_TOPIC = "environment-events-topic";

    @Bean
    public Queue environmentRequestQueue() {
        return new Queue(ENVIRONMENT_REQUEST_QUEUE, false);
    }
}