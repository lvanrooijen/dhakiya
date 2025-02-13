package com.lvr.Dhakiya_backend.entities.environment;

import static com.lvr.Dhakiya_backend.appConfig.Routes.ENVIRONMENTS;

import com.lvr.Dhakiya_backend.entities.environment.dto.GetEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.dto.PatchEnvironment;
import com.lvr.Dhakiya_backend.entities.environment.dto.PostEnvironment;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(ENVIRONMENTS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RestController
@RequiredArgsConstructor
public class EnvironmentController {
  public final EnvironmentService environmentService;

  @PostMapping
  public ResponseEntity<Environment> create(@RequestBody PostEnvironment dto) {
    Environment savedEnvironment = environmentService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedEnvironment.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedEnvironment);
  }

  @GetMapping
  public ResponseEntity<List<Environment>> getAll() {
    List<Environment> environments = environmentService.getAll();
    if (environments.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(environments);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetEnvironment> getById(@PathVariable Long id) {
    return ResponseEntity.ok(environmentService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Environment> patch(
      @PathVariable Long id, @RequestBody PatchEnvironment patch) {
    return ResponseEntity.ok(environmentService.patch(id, patch));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Environment> delete(@PathVariable Long id) {
    environmentService.delete(id);
    return ResponseEntity.ok().build();
  }
}
