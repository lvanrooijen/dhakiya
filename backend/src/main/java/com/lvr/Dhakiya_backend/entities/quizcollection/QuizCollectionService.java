package com.lvr.Dhakiya_backend.entities.quizcollection;

import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentRepository;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetFullQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PatchQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PostQuizCollection;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizCollectionService {
  private final QuizCollectionRepository quizCollectionRepository;
  private final EnvironmentRepository environmentRepository;
  private final QuizRepository quizRepository;

  public GetQuizCollection create(PostQuizCollection dto) {
    QuizCollection created = new QuizCollection(dto.title());

    Environment environment =
        environmentRepository.findById(dto.environmentId()).orElseThrow(NotFoundException::new);
    created.setEnvironment(environment);

    quizCollectionRepository.save(created);
    return GetQuizCollection.from(created);
  }

  public List<GetQuizCollection> getAll() {
    List<GetQuizCollection> quizCollections =
        quizCollectionRepository.findAll().stream()
            .map(quizCollection -> GetQuizCollection.from(quizCollection))
            .toList();

    return quizCollections;
  }

  public GetFullQuizCollection getById(Long id) {
    QuizCollection quizCollection =
        quizCollectionRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Quiz> quizList = quizRepository.findAllByQuizCollection(quizCollection);
    return GetFullQuizCollection.from(quizCollection, quizList);
  }

  public GetQuizCollection patch(Long id, PatchQuizCollection patch) {
    QuizCollection quizCollection =
        quizCollectionRepository.findById(id).orElseThrow(NotFoundException::new);
    if (patch.title() != null) {
      quizCollection.setTitle(patch.title());
    }
    quizCollectionRepository.save(quizCollection);
    return GetQuizCollection.from(quizCollection);
  }

  public void delete(Long id) {
    quizCollectionRepository.findById(id).orElseThrow(NotFoundException::new);
    quizCollectionRepository.deleteById(id);
  }
}
