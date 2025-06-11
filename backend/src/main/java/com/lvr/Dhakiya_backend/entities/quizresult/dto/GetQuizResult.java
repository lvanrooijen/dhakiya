package com.lvr.Dhakiya_backend.entities.quizresult.dto;

import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.GetAnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import java.util.List;

public record GetQuizResult(Long id, List<GetAnsweredQuestion> answeredQuestions) {
  public static GetQuizResult from(QuizResult entity) {
    List<GetAnsweredQuestion> questions =
        entity.getAnsweredQuestions().stream()
            .map(question -> GetAnsweredQuestion.from(question))
            .toList();
    return new GetQuizResult(entity.getId(), questions);
  }
}
