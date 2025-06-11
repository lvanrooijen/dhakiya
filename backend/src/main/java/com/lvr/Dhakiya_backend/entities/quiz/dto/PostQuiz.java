package com.lvr.Dhakiya_backend.entities.quiz.dto;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;

public record PostQuiz(Long quizCollectionId, String title, Integer size) {
  public static Quiz to(PostQuiz dto) {
    Quiz quiz = new Quiz(dto.title);
    if (dto.size != null) {
      if (dto.size < 12) {
        throw new BadRequestException("quiz must be bigger then 12");
      }
      quiz.setSize(dto.size);
    } else {
      quiz.setSize(12);
    }
    return quiz;
  }
}
