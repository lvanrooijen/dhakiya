package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.flashcard.Flashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.FlashcardRepository;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.GetFullFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PatchFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardDeckService {
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardRepository flashcardRepository;
  private final EnvironmentRepository environmentRepository;

  public FlashcardDeck create(PostFlashCardDeck dto) {
    FlashcardDeck createdFlashcardDeck = PostFlashCardDeck.to(dto);
    Environment environment =
        environmentRepository.findById(dto.environmentId()).orElseThrow(NotFoundException::new);
    createdFlashcardDeck.setEnvironment(environment);
    flashcardDeckRepository.save(createdFlashcardDeck);
    return createdFlashcardDeck;
  }

  public List<FlashcardDeck> getAll() {
    return flashcardDeckRepository.findAll();
  }

  public GetFullFlashcardDeck getById(Long id) {
    FlashcardDeck flashcardDeck =
        flashcardDeckRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Flashcard> flashcards = flashcardRepository.findAllByFlashcardDeck(flashcardDeck);
    return GetFullFlashcardDeck.from(flashcardDeck, flashcards);
  }

  public FlashcardDeck patch(Long id, PatchFlashcardDeck patch) {
    FlashcardDeck flashcardDeck =
        flashcardDeckRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      flashcardDeck.setTitle(patch.title());
    }
    return flashcardDeckRepository.save(flashcardDeck);
  }

  public void delete(Long id) {
    flashcardDeckRepository.findById(id).orElseThrow(NotFoundException::new);
    flashcardDeckRepository.deleteById(id);
  }
}
