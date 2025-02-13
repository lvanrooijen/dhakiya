package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Quiz {
  @GeneratedValue @Id Long id;

  @Setter private String title;
  @Setter private Integer size;
  @Setter private Boolean isFinal = false;

  @Setter @ManyToOne private QuizCollection quizCollection;

  public Quiz(String title) {
    this.title = title;
  }
}
