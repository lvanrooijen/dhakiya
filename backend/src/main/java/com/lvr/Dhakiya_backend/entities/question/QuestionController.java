package com.lvr.Dhakiya_backend.entities.question;

import com.lvr.Dhakiya_backend.appConfig.Routes;
import com.lvr.Dhakiya_backend.entities.question.dto.GetQuestion;
import com.lvr.Dhakiya_backend.entities.question.dto.PatchQuestion;
import com.lvr.Dhakiya_backend.entities.question.dto.PostQuestion;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Routes.QUESTIONS)
@CrossOrigin(origins = "${dhakiya.cors}")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping
  public ResponseEntity<GetQuestion> create(@RequestBody PostQuestion question) {
    GetQuestion createdQuestion = questionService.create(question);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdQuestion.id())
            .toUri();
    return ResponseEntity.created(location).body(createdQuestion);
  }

  @GetMapping
  public ResponseEntity<List<GetQuestion>> getAll() {
    List<GetQuestion> questions = questionService.GetAll();
    if (questions.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(questions);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetQuestion> getById(@PathVariable Long id) {
    return ResponseEntity.ok(questionService.getById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetQuestion> patch(
      @PathVariable Long id, @RequestBody PatchQuestion patch) {
    return ResponseEntity.ok(questionService.patch(id, patch));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GetQuestion> delete(@PathVariable Long id) {
    questionService.delete(id);
    return ResponseEntity.ok().build();
  }
}
