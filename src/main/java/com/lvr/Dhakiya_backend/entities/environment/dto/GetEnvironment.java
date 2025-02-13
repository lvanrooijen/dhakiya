package com.lvr.Dhakiya_backend.entities.environment.dto;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.GetFlashcardDeck;
import com.lvr.Dhakiya_backend.entities.notecollection.dto.GetNoteCollection;
import com.lvr.Dhakiya_backend.entities.progressreport.dto.GetProgressReport;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.dto.DetailedTag;
import java.util.List;

public record GetEnvironment(
    Long id,
    String title,
    GetNoteCollection noteCollection,
    List<GetFlashcardDeck> flashcardDecks,
    List<GetQuizCollection> quizCollections,
    GetProgressReport progressReport,
    List<DetailedTag> tags) {
  public static GetEnvironment from(
      Environment environment,
      GetNoteCollection noteCollection,
      List<FlashcardDeck> flashcardDecks,
      List<QuizCollection> quizCollections,
      GetProgressReport progressReport,
      List<Tag> tags) {
    List<GetFlashcardDeck> formattedFlashcardDecks =
        flashcardDecks.stream().map(GetFlashcardDeck::from).toList();
    List<GetQuizCollection> formattedQuizCollectons =
        quizCollections.stream().map(GetQuizCollection::from).toList();
    List<DetailedTag> detailedTags = tags.stream().map(DetailedTag::from).toList();
    return new GetEnvironment(
        environment.getId(),
        environment.getTitle(),
        noteCollection,
        formattedFlashcardDecks,
        formattedQuizCollectons,
        progressReport,
        detailedTags);
  }
}
