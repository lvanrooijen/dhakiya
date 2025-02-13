package com.lvr.Dhakiya_backend.entities.note;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.note.dto.GetNote;
import com.lvr.Dhakiya_backend.entities.note.dto.PatchNote;
import com.lvr.Dhakiya_backend.entities.note.dto.PostNote;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.NOTES)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class NoteController {
  private final NoteService noteService;

  @PostMapping
  public ResponseEntity<GetNote> create(@RequestBody PostNote note) {
    GetNote savedNote = noteService.create(note);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedNote.id())
            .toUri();

    return ResponseEntity.created(location).body(savedNote);
  }

  @GetMapping
  public ResponseEntity<List<GetNote>> getAll() {
    List<GetNote> notes = noteService.getAll();
    if (notes.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(notes);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetNote> getById(@PathVariable Long id) {
    return ResponseEntity.ok(noteService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetNote> patch(@PathVariable Long id, @RequestBody PatchNote patch) {
    return ResponseEntity.ok(noteService.patch(id, patch));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GetNote> delete(@PathVariable Long id) {
    noteService.delete(id);
    return ResponseEntity.ok().build();
  }
}
