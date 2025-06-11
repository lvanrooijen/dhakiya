package com.lvr.Dhakiya_backend.entities.note.dto;

import com.lvr.Dhakiya_backend.entities.note.Note;
import com.lvr.Dhakiya_backend.entities.tag.dto.GetTag;

public record GetNote(
    Long id, Long noteCollectionId, Long environmentId, String title, String content, GetTag tag) {
  public static GetNote from(Note note) {
    GetTag tag = null;
    if (note.getTag() != null) {
      tag = GetTag.from(note.getTag());
    }
    return new GetNote(
        note.getId(),
        note.getNoteCollection().getId(),
        note.getNoteCollection().getEnvironment().getId(),
        note.getTitle(),
        note.getContent(),
        tag);
  }
}
