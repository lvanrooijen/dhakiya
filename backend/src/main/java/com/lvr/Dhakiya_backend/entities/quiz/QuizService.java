package com.lvr.Dhakiya_backend.entities.quiz;

import com.lvr.Dhakiya_backend.entities.question.Question;
import com.lvr.Dhakiya_backend.entities.question.QuestionRepository;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetFullQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PatchQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PostQuiz;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollectionRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
  private final QuizRepository quizRepository;
  private final QuizCollectionRepository quizCollectionRepository;
  private final QuestionRepository questionRepository;

  // TODO als je een quiz aanmaakt deze vullen met lege questions?
  public GetQuiz create(PostQuiz dto) {
    Quiz createdQuiz = PostQuiz.to(dto);

    QuizCollection quizCollection =
        quizCollectionRepository
            .findById(dto.quizCollectionId())
            .orElseThrow(NotFoundException::new);
    createdQuiz.setQuizCollection(quizCollection);

    quizRepository.save(createdQuiz);

    return GetQuiz.from(createdQuiz);
  }

  public List<GetQuiz> getAll() {
    List<Quiz> quizzes = quizRepository.findAll();
    return quizzes.stream().map(GetQuiz::from).toList();
  }

  public GetFullQuiz getById(Long id) {
    Quiz quiz = quizRepository.findById(id).orElseThrow(NotFoundException::new);
    List<Question> questions = questionRepository.findQuestionByQuiz(quiz);
    return GetFullQuiz.from(quiz, questions);
  }

  public GetQuiz patch(Long id, PatchQuiz patch) {
    Quiz quiz = quizRepository.findById(id).orElseThrow(NotFoundException::new);
    if (quiz.getIsFinal()) {
      throw new BadRequestException("Editing a quiz after it has been saved is not allowed");
    }

    if (patch.title() != null) {
      quiz.setTitle(patch.title());
    }

    if (patch.isFinal() != null) {
      if (quiz.getSize() != questionRepository.findQuestionByQuiz(quiz).size()) {
        throw new BadRequestException("Can not finalize incomplete quiz");
      }
      quiz.setIsFinal(patch.isFinal());
    }

    quizRepository.save(quiz);
    return GetQuiz.from(quiz);
  }

  public void delete(Long id) {
    quizRepository.findById(id).orElseThrow(NotFoundException::new);
    quizRepository.deleteById(id);
  }
}
