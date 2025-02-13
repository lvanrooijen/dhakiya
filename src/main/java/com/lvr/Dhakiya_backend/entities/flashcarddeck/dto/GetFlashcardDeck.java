package com.lvr.Dhakiya_backend.entities.flashcarddeck.dto;

import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;

public record GetFlashcardDeck(Long id, String title) {
  public static GetFlashcardDeck from(FlashcardDeck entity) {
    return new GetFlashcardDeck(entity.getId(), entity.getTitle());
  }
}
