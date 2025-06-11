package com.lvr.Dhakiya_backend.entities.answer;

import com.lvr.Dhakiya_backend.entities.answer.dto.GetAnswer;
import com.lvr.Dhakiya_backend.entities.answer.dto.PatchAnswer;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;

  public List<GetAnswer> getAll() {
    return answerRepository.findAll().stream().map(answer -> GetAnswer.from(answer)).toList();
  }

  public GetAnswer patch(Long id, PatchAnswer patch) {
    Answer answer = answerRepository.findById(id).orElseThrow(NotFoundException::new);

    if (patch.answer() != null) {
      answer.setAnswer(patch.answer());
    }
    if (patch.isCorrect() != null) {
      answer.setIsCorrect(patch.isCorrect());
    }

    answerRepository.save(answer);
    return GetAnswer.from(answer);
  }
}
