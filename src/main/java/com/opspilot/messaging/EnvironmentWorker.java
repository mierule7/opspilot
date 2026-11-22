package com.opspilot.messaging;

import com.opspilot.config.MessagingConfig;
import com.opspilot.entity.EnvironmentRequest;
import com.opspilot.repository.EnvironmentRequestRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnvironmentWorker {

    @Autowired
    private EnvironmentRequestRepository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RabbitListener(queues = MessagingConfig.ENVIRONMENT_REQUEST_QUEUE)
    public void processEnvironmentRequest(String requestId) {
        System.out.println("Worker received request ID: " + requestId);

        Optional<EnvironmentRequest> optionalRequest = repository.findById(Long.parseLong(requestId));

        if (optionalRequest.isPresent()) {
            EnvironmentRequest request = optionalRequest.get();
            request.setStatus("PROVISIONING");
            repository.save(request);

            // Simulate the time it takes to provision infra (e.g., Terraform/Ansible)
            try {
                Thread.sleep(5000); // 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            request.setStatus("COMPLETED");
            repository.save(request);
            System.out.println("Worker completed provisioning for ID: " + requestId);

            // Emit Kafka event
            String eventMessage = "EnvironmentProvisioned:" + request.getEnvironmentName();
            kafkaTemplate.send(MessagingConfig.ENVIRONMENT_EVENTS_TOPIC, eventMessage);
            System.out.println("Emitted Kafka event: " + eventMessage);
        }
    }
}