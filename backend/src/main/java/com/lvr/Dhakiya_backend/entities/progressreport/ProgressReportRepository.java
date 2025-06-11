package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressReportRepository extends JpaRepository<ProgressReport, Long> {
  Optional<ProgressReport> findByEnvironment(Environment environment);
}
