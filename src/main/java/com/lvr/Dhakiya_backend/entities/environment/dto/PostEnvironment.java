package com.lvr.Dhakiya_backend.entities.environment.dto;

import com.lvr.Dhakiya_backend.entities.environment.Environment;

public record PostEnvironment(String title) {
  public static Environment to(PostEnvironment dto) {
    return new Environment(dto.title);
  }
}
