package com.lvr.Dhakiya_backend.entities.question.dto;

import com.lvr.Dhakiya_backend.entities.answer.dto.PostAnswer;
import com.lvr.Dhakiya_backend.entities.question.Question;
import java.util.List;

public record PostQuestion(
    Long quizId, String question, Integer answerCount, List<PostAnswer> answers, Long tagId) {
  public static Question to(PostQuestion dto) {
    return new Question(dto.question, dto.answerCount);
  }
}
