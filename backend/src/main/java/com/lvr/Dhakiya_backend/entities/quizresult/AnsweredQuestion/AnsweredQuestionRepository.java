package com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion;

import com.lvr.Dhakiya_backend.entities.quizresult.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long> {}
