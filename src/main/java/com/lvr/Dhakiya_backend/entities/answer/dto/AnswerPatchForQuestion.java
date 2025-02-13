package com.lvr.Dhakiya_backend.entities.answer.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;

public record AnswerPatchForQuestion(Long id, String answer, Boolean isCorrect) {
  public static Answer to(AnswerPatchForQuestion dto) {
    return new Answer(dto.answer, dto.isCorrect);
  }
}
