package com.lvr.Dhakiya_backend.entities.note.dto;

import com.lvr.Dhakiya_backend.entities.note.Note;

public record PostNote(Long noteCollectionId, String title, String content, Long tagId) {
  public static Note to(PostNote dto) {
    return new Note(dto.title, dto.content);
  }
}
