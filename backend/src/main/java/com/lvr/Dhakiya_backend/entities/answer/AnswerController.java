package com.lvr.Dhakiya_backend.entities.answer;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.answer.dto.GetAnswer;
import com.lvr.Dhakiya_backend.entities.answer.dto.PatchAnswer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.ANSWERS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class AnswerController {
  private final AnswerService answerService;

  @GetMapping
  public ResponseEntity<List<GetAnswer>> getAll() {
    return ResponseEntity.ok(answerService.getAll());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetAnswer> patch(@PathVariable Long id, @RequestBody PatchAnswer patch) {
    return ResponseEntity.ok(answerService.patch(id, patch));
  }
}
