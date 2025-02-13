package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.entities.note.dto.GetNote;
import com.lvr.Dhakiya_backend.entities.note.dto.PatchNote;
import com.lvr.Dhakiya_backend.entities.note.dto.PostNote;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollectionRepository;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final TagRepository tagRepository;

  public GetNote create(PostNote note) {
    if (note.noteCollectionId() == null || note.title() == null || note.content() == null) {
      throw new BadRequestException("Incomplete body");
    }
    Note savedNote = PostNote.to(note);
    NoteCollection noteCollection =
        noteCollectionRepository
            .findById(note.noteCollectionId())
            .orElseThrow(NotFoundException::new);
    savedNote.setNoteCollection(noteCollection);
    if (note.tagId() != null) {
      Tag tag = tagRepository.findById(note.tagId()).orElseThrow(NotFoundException::new);
      savedNote.setTag(tag);
    }
    noteRepository.save(savedNote);
    return GetNote.from(savedNote);
  }

  public List<GetNote> getAll() {
    return noteRepository.findAll().stream().map(note -> GetNote.from(note)).toList();
  }

  public GetNote getById(Long id) {
    Note note = noteRepository.findById(id).orElseThrow(NotFoundException::new);
    return GetNote.from(note);
  }

  public GetNote patch(Long id, PatchNote patch) {
    Note note = noteRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      note.setTitle(patch.title());
    }
    if (patch.content() != null) {
      note.setContent(patch.content());
    }
    if (patch.tagId() != null) {
      Tag tag = tagRepository.findById(patch.tagId()).orElseThrow(NotFoundException::new);
      note.setTag(tag);
    }
    noteRepository.save(note);
    return GetNote.from(note);
  }

  public void delete(Long id) {
    noteRepository.findById(id).orElseThrow(NotFoundException::new);
    noteRepository.deleteById(id);
  }
}
