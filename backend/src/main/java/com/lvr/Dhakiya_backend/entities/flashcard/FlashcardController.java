package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.GetFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.PatchFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.PostFlashcard;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.FLASHCARDS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class FlashcardController {
  private final FlashcardService flashcardService;

  @PostMapping
  public ResponseEntity<GetFlashcard> create(@RequestBody PostFlashcard flashcard) {
    GetFlashcard createdFlashcard = flashcardService.create(flashcard);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdFlashcard.id())
            .toUri();

    return ResponseEntity.created(location).body(createdFlashcard);
  }

  @GetMapping
  public ResponseEntity<List<GetFlashcard>> getAll() {
    List<GetFlashcard> flashcards = flashcardService.getAll();
    if (flashcards.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(flashcards);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetFlashcard> getById(@PathVariable Long id) {
    return ResponseEntity.ok(flashcardService.getById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GetFlashcard> delete(@PathVariable Long id) {
    flashcardService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetFlashcard> patch(
      @PathVariable Long id, @RequestBody PatchFlashcard patch) {
    return ResponseEntity.ok(flashcardService.patch(id, patch));
  }
}
