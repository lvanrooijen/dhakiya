package com.lvr.Dhakiya_backend.entities.answer.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;

public record GetAnswer(Long id, String answer, Boolean isCorrect) {
  public static GetAnswer from(Answer entity) {
    return new GetAnswer(entity.getId(), entity.getAnswer(), entity.getIsCorrect());
  }
}
