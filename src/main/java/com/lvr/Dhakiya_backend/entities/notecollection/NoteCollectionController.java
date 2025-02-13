package com.lvr.Dhakiya_backend.entities.notecollection;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.notecollection.dto.GetNoteCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.NOTE_COLLECTIONS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class NoteCollectionController {
  private final NoteCollectionService noteCollectionService;

  @GetMapping("/{id}")
  public ResponseEntity<GetNoteCollection> getById(@PathVariable Long id) {
    GetNoteCollection noteCollection = noteCollectionService.getById(id);
    return ResponseEntity.ok(noteCollection);
  }
}
