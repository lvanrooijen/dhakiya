package com.lvr.Dhakiya_backend.entities.progressreport;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProgressReport {
  @GeneratedValue @Id Long id;
  @OneToOne @Setter private Environment environment;

  public ProgressReport(Environment environment) {
    this.environment = environment;
  }
}
