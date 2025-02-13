package com.lvr.Dhakiya_backend.entities.flashcard.dto;

import com.lvr.Dhakiya_backend.entities.flashcard.Flashcard;

public record PostFlashcard(
    Long flashcardDeckId, Long tagId, String title, String content, Integer minimumDisplays) {
  public static Flashcard to(PostFlashcard dto) {
    return new Flashcard(dto.title, dto.content);
  }
}
