package com.lvr.Dhakiya_backend.entities.flashcarddeck.dto;

import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;

public record PostFlashCardDeck(Long environmentId, String title) {
  public static FlashcardDeck to(PostFlashCardDeck dto) {
    return new FlashcardDeck(dto.title);
  }
}
