package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.PatchAnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.SubmitQuizResult;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(Routes.QUIZ_RESULTS)
@CrossOrigin(origins = "${dhakiya.cors}")
public class QuizResultController {
  private final QuizResultService quizResultService;

  @PostMapping
  public ResponseEntity<GetQuizResult> create(@RequestBody PostQuizResult dto) {
    GetQuizResult createdQuizResult = quizResultService.create(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdQuizResult.id())
            .toUri();
    return ResponseEntity.created(location).body(createdQuizResult);
  }

  @GetMapping
  public ResponseEntity<List<GetQuizResult>> getAll() {
    List<GetQuizResult> results = quizResultService.getAll();
    if (results.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(results);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetQuizResult> getById(@PathVariable Long id) {
    return ResponseEntity.ok(quizResultService.getById(id));
  }

  @PatchMapping("/{id}/submit-answer")
  public ResponseEntity<GetQuizResult> submitAnswer(
      @PathVariable Long id, @RequestBody PatchAnsweredQuestion patch) {
    return ResponseEntity.ok(quizResultService.submitAnswer(id, patch));
  }

  @PatchMapping("/{id}/submit")
  public ResponseEntity<SubmitQuizResult> submit(@PathVariable Long id) {
    return ResponseEntity.ok(quizResultService.submit(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<QuizResult> delete(@PathVariable Long id) {
    quizResultService.delete(id);
    return ResponseEntity.ok().build();
  }
}
