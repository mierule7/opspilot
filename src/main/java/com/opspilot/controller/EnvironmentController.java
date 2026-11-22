package com.opspilot.controller;

import com.opspilot.entity.EnvironmentRequest;
import com.opspilot.messaging.EnvironmentRequestProducer;
import com.opspilot.repository.EnvironmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/environments")
public class EnvironmentController {

    @Autowired
    private EnvironmentRequestRepository repository;

    @Autowired
    private EnvironmentRequestProducer producer;

    @PostMapping
    public ResponseEntity<EnvironmentRequest> requestEnvironment(@RequestBody EnvironmentRequest request) {
        request.setStatus("PENDING");
        EnvironmentRequest savedRequest = repository.save(request);

        // Send to RabbitMQ
        producer.sendEnvironmentRequest(savedRequest);

        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping
    public ResponseEntity<List<EnvironmentRequest>> getAllRequests() {
        return ResponseEntity.ok(repository.findAll());
    }
}