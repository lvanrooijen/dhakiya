package com.lvr.Dhakiya_backend.entities.quizresult.dto;

import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import java.math.BigDecimal;

public record SubmitQuizResult(Long id, BigDecimal score) {
  public static SubmitQuizResult from(QuizResult entity) {
    return new SubmitQuizResult(entity.getId(), entity.getScore());
  }
}
