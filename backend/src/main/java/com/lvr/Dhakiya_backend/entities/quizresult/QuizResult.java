package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class QuizResult {
  @GeneratedValue @Id private Long id;

  @Setter private LocalDate createdOn;
  @Setter private Boolean isCompleted = false;
  @Setter private int points = 0;

  @Setter
  @OneToMany(cascade = CascadeType.REMOVE)
  List<AnsweredQuestion> answeredQuestions = new ArrayList<>();

  @Setter @ManyToOne Quiz quiz;

  public QuizResult(Quiz quiz) {
    this.quiz = quiz;
  }

  public void addPoint() {
    this.points++;
  }

  public BigDecimal getScore() {
    if (points == 0 || answeredQuestions.isEmpty())
      return new BigDecimal(0).setScale(1, RoundingMode.DOWN);
    double score = (double) points / answeredQuestions.size() * 100;
    return new BigDecimal(score).setScale(1, RoundingMode.DOWN);
  }
}
