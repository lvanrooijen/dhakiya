package com.lvr.Dhakiya_backend.entities.environment;

import com.lvr.Dhakiya_backend.entities.environment.dto.GetEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.dto.PatchEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.dto.PostEnvironment;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckRepository;
import com.lvr.Dhakiya_backend.entities.note.Note;
import com.lvr.Dhakiya_backend.entities.note.NoteRepository;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollectionRepository;
import com.lvr.Dhakiya_backend.entities.notecollection.dto.GetNoteCollection;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReport;
import com.lvr.Dhakiya_backend.entities.progressreport.ProgressReportRepository;
import com.lvr.Dhakiya_backend.entities.progressreport.dto.GetProgressReport;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollectionRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;
  private final TagRepository tagRepository;
  private final ProgressReportRepository progressReportRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final QuizCollectionRepository quizCollectionRepository;
  private final NoteRepository noteRepository;

  public Environment create(PostEnvironment dto) {
    Environment newEnvironment = PostEnvironment.to(dto);
    environmentRepository.save(newEnvironment);

    ProgressReport progressReport = new ProgressReport(newEnvironment);
    progressReportRepository.save(progressReport);

    NoteCollection noteCollection = new NoteCollection(newEnvironment);
    noteCollectionRepository.save(noteCollection);

    return newEnvironment;
  }

  public List<Environment> getAll() {
    return environmentRepository.findAll();
  }

  public GetEnvironment getById(Long id) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);

    NoteCollection noteCollection =
        noteCollectionRepository.findByEnvironment(environment).orElseThrow(NotFoundException::new);
    List<Note> notes = noteRepository.findAllByNoteCollection(noteCollection);

    List<FlashcardDeck> flashcardDecks = flashcardDeckRepository.findAllByEnvironment(environment);
    List<QuizCollection> quizCollections =
        quizCollectionRepository.findAllByEnvironment(environment);
    GetProgressReport progressReport =
        GetProgressReport.from(
            progressReportRepository
                .findByEnvironment(environment)
                .orElseThrow(NotFoundException::new),
            tagRepository.findByEnvironment(environment));
    List<Tag> tags = tagRepository.findByEnvironment(environment);
    return GetEnvironment.from(
        environment,
        GetNoteCollection.from(noteCollection, notes),
        flashcardDecks,
        quizCollections,
        progressReport,
        tags);
  }

  public Environment patch(Long id, PatchEnvironment patch) {
    Environment patchedEnvironment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      patchedEnvironment.setTitle(patch.title());
    }
    return environmentRepository.save(patchedEnvironment);
  }

  public void delete(Long id) {
    Environment environment =
        environmentRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Tag> relatedTags = tagRepository.findByEnvironment(environment);
    if (!relatedTags.isEmpty()) {
      tagRepository.deleteAll(relatedTags);
    }
    environmentRepository.deleteById(id);
  }
}
