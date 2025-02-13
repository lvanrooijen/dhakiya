package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  public List<Note> findAllByNoteCollection(NoteCollection noteCollection);
}
