package com.lvr.Dhakiya_backend.entities.answer.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;

public record PatchAnswer(String answer, Boolean isCorrect) {
  public static Answer to(PatchAnswer dto) {
    return new Answer(dto.answer, dto.isCorrect);
  }
}
