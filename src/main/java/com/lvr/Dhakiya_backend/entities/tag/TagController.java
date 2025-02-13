package com.lvr.Dhakiya_backend.entities.tag;

import static com.lvr.Dhakiya_backend.appConfig.Routes.TAGS;

import com.lvr.Dhakiya_backend.entities.tag.dto.PatchTag;
import com.lvr.Dhakiya_backend.entities.tag.dto.PostTag;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(TAGS)
@RequiredArgsConstructor
@CrossOrigin(origins = "${dhakiya.cors}")
public class TagController {
  private final TagService tagService;

  @PostMapping
  public ResponseEntity<Tag> create(@RequestBody PostTag dto) {
    Tag savedTag = tagService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedTag.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedTag);
  }

  @GetMapping
  public ResponseEntity<List<Tag>> getAll(@RequestParam(required = false) Long environmentId) {
    List<Tag> tags = tagService.getAll(environmentId);
    if (tags.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(tags);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tag> getById(@PathVariable Long id) {
    return ResponseEntity.ok(tagService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Tag> patch(@PathVariable Long id, @RequestBody PatchTag patch) {
    Tag patchedTag = tagService.patch(id, patch);
    return ResponseEntity.ok(patchedTag);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> delete(@PathVariable Long id) {
    tagService.delete(id);
    return ResponseEntity.ok().build();
  }
}
