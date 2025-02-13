package com.lvr.Dhakiya_backend.entities.answer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Answer {
  @GeneratedValue @Id private Long id;

  @Setter private String answer;
  @Setter private Boolean isCorrect;

  public Answer(String answer, Boolean isCorrect) {
    this.answer = answer;
    this.isCorrect = isCorrect;
  }
}
