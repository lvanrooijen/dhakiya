package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.flashcard.enums.FlashcardFlags;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Flashcard {
  @GeneratedValue @Id private Long id;

  @Setter private String title;
  @Setter private String content;
  @Setter private int seenCount = 0;

  @Setter private Integer minimumDisplayCount = 5;
  @Setter private Integer scorePoints = 0;

  @Setter private LocalDate lastSeen;
  private final LocalDate createdOn = LocalDate.now();

  @Setter @ManyToOne Tag tag;

  @Setter @ManyToOne FlashcardDeck flashcardDeck;

  public Flashcard(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Status getStatus() {
    if (seenCount == 0 || seenCount < minimumDisplayCount) {
      return Status.NO_DATA;
    }

    double performanceScore = (double) scorePoints / seenCount;

    if (performanceScore >= 4) {
      return Status.STRONG;
    } else if (performanceScore >= 3) {
      return Status.GOOD;
    } else if (performanceScore >= 2.5) {
      return Status.MEDIOCRE;
    } else {
      return Status.WEAK;
    }
  }

  public void updateScore(FlashcardFlags flag) {
    if (flag == FlashcardFlags.FLAGGED_EASY) {
      scorePoints += 4;
    } else if (flag == FlashcardFlags.CORRECT) {
      scorePoints += 3;
    } else if (flag == FlashcardFlags.INCORRECT) {
      scorePoints += 2;
    } else if (flag == FlashcardFlags.FLAGGED_DIFFICULT) {
      scorePoints += 1;
    }
    lastSeen = LocalDate.now();
  }

  public void markAsSeen() {
    this.seenCount++;
  }
}
