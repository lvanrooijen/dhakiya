package com.lvr.Dhakiya_backend.entities.notecollection.dto;

import com.lvr.Dhakiya_backend.entities.note.Note;
import com.lvr.Dhakiya_backend.entities.note.dto.GetNote;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import java.util.List;

public record GetNoteCollection(Long id, List<GetNote> notes) {
  public static GetNoteCollection from(NoteCollection noteCollection, List<Note> notes) {
    List<GetNote> transformedNotes = notes.stream().map(note -> GetNote.from(note)).toList();

    return new GetNoteCollection(noteCollection.getId(), transformedNotes);
  }
}
