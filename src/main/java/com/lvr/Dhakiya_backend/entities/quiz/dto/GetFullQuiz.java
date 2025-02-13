package com.lvr.Dhakiya_backend.entities.quiz.dto;

import com.lvr.Dhakiya_backend.entities.question.Question;
import com.lvr.Dhakiya_backend.entities.question.dto.GetQuestion;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import java.util.List;

public record GetFullQuiz(
    Long id, String title, Integer size, Boolean isFinal, List<GetQuestion> questions) {
  public static GetFullQuiz from(Quiz quiz, List<Question> questions) {
    List<GetQuestion> convertedQuestions =
        questions.stream().map(question -> GetQuestion.from(question)).toList();
    return new GetFullQuiz(
        quiz.getId(), quiz.getTitle(), quiz.getSize(), quiz.getIsFinal(), convertedQuestions);
  }
}
