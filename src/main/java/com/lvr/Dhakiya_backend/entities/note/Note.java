package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Note {
  @GeneratedValue @Id Long id;

  @Setter private String title;

  @Column(length = 500)
  @Setter
  private String content;

  @Setter @ManyToOne private NoteCollection noteCollection;

  @Setter @ManyToOne private Tag tag;

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
