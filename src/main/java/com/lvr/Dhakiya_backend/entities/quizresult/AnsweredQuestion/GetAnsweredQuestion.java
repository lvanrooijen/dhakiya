package com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion;

import com.lvr.Dhakiya_backend.entities.answer.dto.GetCompactAnswer;
import java.util.ArrayList;
import java.util.List;

public record GetAnsweredQuestion(Long id, String question, List<GetCompactAnswer> answers) {
  public static GetAnsweredQuestion from(AnsweredQuestion entity) {
    List<GetCompactAnswer> answers =
        entity.getSelectedAnswers() != null
            ? entity.getSelectedAnswers().stream()
                .map(answer -> GetCompactAnswer.from(answer))
                .toList()
            : new ArrayList<>();
    return new GetAnsweredQuestion(entity.getId(), entity.question.getQuestion(), answers);
  }
}
