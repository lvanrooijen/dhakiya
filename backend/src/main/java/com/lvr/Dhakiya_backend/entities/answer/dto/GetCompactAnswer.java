package com.lvr.Dhakiya_backend.entities.answer.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;

public record GetCompactAnswer(Long id, String answer) {
  public static GetCompactAnswer from(Answer entity) {
    return new GetCompactAnswer(entity.getId(), entity.getAnswer());
  }
}
