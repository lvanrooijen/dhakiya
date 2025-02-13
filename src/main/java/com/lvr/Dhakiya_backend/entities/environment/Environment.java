package com.lvr.Dhakiya_backend.entities.environment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Environment {
  @GeneratedValue @Id private Long id;

  @Setter private String title;

  public Environment(String title) {
    this.title = title;
  }
}
