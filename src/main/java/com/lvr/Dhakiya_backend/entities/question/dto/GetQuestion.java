package com.lvr.Dhakiya_backend.entities.question.dto;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.question.Question;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;
import java.util.List;

public record GetQuestion(Long id, Long quizId, String question, List<Answer> answers, GetTag tag) {
  public static GetQuestion from(Question question) {
    GetTag tag = null;
    if (question.getTag() != null) {
      tag = GetTag.from(question.getTag());
    }

    return new GetQuestion(
        question.getId(),
        question.getQuiz().getId(),
        question.getQuestion(),
        question.getAnswers(),
        tag);
  }
}
