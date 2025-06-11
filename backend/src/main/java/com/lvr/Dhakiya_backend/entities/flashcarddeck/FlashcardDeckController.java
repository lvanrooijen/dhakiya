package com.lvr.Dhakiya_backend.entities.flashcarddeck;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.GetFullFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PatchFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.FLASHCARD_DECKS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class FlashcardDeckController {
  private final FlashcardDeckService flashcardDeckService;

  @PostMapping
  public ResponseEntity<FlashcardDeck> create(@RequestBody PostFlashCardDeck flashcardDeck) {
    FlashcardDeck createdFlashcardDeck = flashcardDeckService.create(flashcardDeck);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdFlashcardDeck.getId())
            .toUri();
    return ResponseEntity.created(location).body(createdFlashcardDeck);
  }

  @GetMapping
  public ResponseEntity<List<FlashcardDeck>> getAll() {
    List<FlashcardDeck> flashcardDecks = flashcardDeckService.getAll();
    if (flashcardDecks.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcardDecks);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetFullFlashcardDeck> getById(@PathVariable Long id) {
    return ResponseEntity.ok(flashcardDeckService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<FlashcardDeck> patch(
      @PathVariable Long id, @RequestBody PatchFlashcardDeck patch) {
    FlashcardDeck patchedFlashcardDeck = flashcardDeckService.patch(id, patch);
    return ResponseEntity.ok(patchedFlashcardDeck);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<FlashcardDeck> delete(@PathVariable Long id) {
    flashcardDeckService.delete(id);
    return ResponseEntity.ok().build();
  }
}
