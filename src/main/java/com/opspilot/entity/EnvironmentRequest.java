package com.opspilot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "environment_requests")
public class EnvironmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String environmentName; // e.g., "dev-feature-x"

    @Column(nullable = false)
    private String environmentType; // e.g., "DEV", "UAT", "STAGING"

    @Column(nullable = false)
    private String requestedBy; // username of the requester

    @Column(nullable = false)
    private String status; // e.g., "PENDING", "PROVISIONING", "COMPLETED", "FAILED"

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}