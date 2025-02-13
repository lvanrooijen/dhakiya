package com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.question.Question;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class AnsweredQuestion {
  @GeneratedValue @Id private Long id;

  @ManyToOne(cascade = CascadeType.DETACH)
  Question question;

  @Setter
  @OneToMany(cascade = CascadeType.DETACH)
  private List<Answer> selectedAnswers;

  private Integer validAnswerCount;

  public AnsweredQuestion(Question question, Integer validAnswerCount) {
    this.question = question;
    this.validAnswerCount = validAnswerCount;
  }
}
