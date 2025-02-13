package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.flashcard.dto.GetFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.PatchFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.PostFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcard.enums.FlashcardFlags;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardRepository flashcardRepository;
  private final TagRepository tagRepository;

  public GetFlashcard create(PostFlashcard dto) {
    Flashcard createdFlashcard = PostFlashcard.to(dto);

    if (dto.tagId() != null) {
      Tag tag = tagRepository.findById(dto.tagId()).orElseThrow(NotFoundException::new);
      createdFlashcard.setTag(tag);
    }

    if (dto.minimumDisplays() != null) {
      if (dto.minimumDisplays() < 5) {
        throw new BadRequestException("Flashcard minimum must be set to 5 or more");
      }
      createdFlashcard.setMinimumDisplayCount(dto.minimumDisplays());
    }

    FlashcardDeck deck =
        flashcardDeckRepository.findById(dto.flashcardDeckId()).orElseThrow(NotFoundException::new);

    createdFlashcard.setFlashcardDeck(deck);

    flashcardRepository.save(createdFlashcard);

    return GetFlashcard.from(createdFlashcard);
  }

  public List<GetFlashcard> getAll() {
    List<GetFlashcard> flashcards =
        flashcardRepository.findAll().stream()
            .map(flashcard -> GetFlashcard.from(flashcard))
            .toList();

    return flashcards;
  }

  public GetFlashcard getById(Long id) {
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);

    return GetFlashcard.from(flashcard);
  }

  public void delete(Long id) {
    flashcardRepository.findById(id).orElseThrow(NotFoundException::new);

    flashcardRepository.deleteById(id);
  }

  public GetFlashcard patch(Long id, PatchFlashcard patch) {
    Flashcard flashcard = flashcardRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.title() != null) {
      flashcard.setTitle(patch.title());
    }
    if (patch.content() != null) {
      flashcard.setContent(patch.content());
    }
    if (patch.tagId() != null) {
      Tag tag = tagRepository.findById(patch.tagId()).orElseThrow(NotFoundException::new);
      flashcard.setTag(tag);
    }

    if (patch.flag() != null) {
      try {
        FlashcardFlags flag = FlashcardFlags.valueOf(patch.flag().toUpperCase());
        flashcard.markAsSeen();
        flashcard.updateScore(flag);
        if (flashcard.tag != null || patch.tagId() == null) {
          Tag tag = flashcard.getTag();
          if (tag != null) {
            tag.markAsSeen();
            if (flag == FlashcardFlags.CORRECT || flag == FlashcardFlags.FLAGGED_EASY) {
              tag.flagPositive();
            }
            tagRepository.save(tag);
          }
        }
      } catch (IllegalArgumentException exception) {
        throw new BadRequestException("False flag");
      }
      flashcard.setSeenCount(flashcard.getSeenCount() + 1);
    }

    flashcardRepository.save(flashcard);
    return GetFlashcard.from(flashcard);
  }
}
