package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
  public List<Question> findQuestionByQuiz(Quiz quiz);
}
