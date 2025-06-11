package com.lvr.Dhakiya_backend.entities.answer.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;

public record PostAnswer(String answer, Boolean isCorrect) {
  public static Answer to(PostAnswer dto) {
    return new Answer(dto.answer(), dto.isCorrect());
  }
}
