package com.lvr.Dhakiya_backend.entities.quizcollection.dto;

import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;

public record GetQuizCollection(Long id, Long environmentId, String title) {
  public static GetQuizCollection from(QuizCollection quizCollection) {
    return new GetQuizCollection(
        quizCollection.getId(), quizCollection.getEnvironment().getId(), quizCollection.getTitle());
  }
}
