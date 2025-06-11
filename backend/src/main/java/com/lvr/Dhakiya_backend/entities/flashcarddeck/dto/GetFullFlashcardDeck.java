package com.lvr.Dhakiya_backend.entities.flashcarddeck.dto;

import com.lvr.Dhakiya_backend.entities.flashcard.Flashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.GetFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import java.util.List;

public record GetFullFlashcardDeck(Long id, String title, List<GetFlashcard> flashcards) {
  public static GetFullFlashcardDeck from(FlashcardDeck flashcardDeck, List<Flashcard> flashcards) {
    List<GetFlashcard> getFlashcards =
        flashcards.stream().map(flashcard -> GetFlashcard.from(flashcard)).toList();
    return new GetFullFlashcardDeck(flashcardDeck.getId(), flashcardDeck.getTitle(), getFlashcards);
  }
}
