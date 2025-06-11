package com.lvr.Dhakiya_backend.entities.flashcard;

import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    public List<Flashcard> findAllByFlashcardDeck(FlashcardDeck flashcardDeck);
}
