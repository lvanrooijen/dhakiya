package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.entities.note.Note;
import com.lvr.Dhakiya_backend.entities.note.NoteRepository;
import com.lvr.Dhakiya_backend.entities.notecollection.dto.GetNoteCollection;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;

  public List<NoteCollection> getAll() {
    return noteCollectionRepository.findAll();
  }

  public GetNoteCollection getById(Long id) {
    NoteCollection noteCollection =
        noteCollectionRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Note> notes = noteRepository.findAllByNoteCollection(noteCollection);
    return GetNoteCollection.from(noteCollection, notes);
  }
}
