package com.opspilot.repository;

import com.opspilot.entity.EnvironmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRequestRepository extends JpaRepository<EnvironmentRequest, Long> {
}