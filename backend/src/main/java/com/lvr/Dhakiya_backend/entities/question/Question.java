package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Question {
  @GeneratedValue @Id private Long id;

  @Setter @ManyToOne Quiz quiz;
  @Setter private String question;

  @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  List<Answer> answers = new ArrayList<>();

  @Setter private Integer answerCount;
  @Setter @ManyToOne private Tag tag;

  public Question(String question, Integer answerCount) {
    this.question = question;
    this.answerCount = answerCount;
  }

  public Question(Quiz quiz, String question, List<Answer> answers, Tag tag) {
    this.quiz = quiz;
    this.question = question;
    this.answers = answers;
    this.tag = tag;
  }

  public void add(List<Answer> answers) {
    this.answers.addAll(answers);
  }
}
