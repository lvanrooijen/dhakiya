package com.lvr.Dhakiya_backend.entities.quizresult;

import com.lvr.Dhakiya_backend.entities.answer.Answer;
import com.lvr.Dhakiya_backend.entities.answer.AnswerRepository;
import com.lvr.Dhakiya_backend.entities.question.Question;
import com.lvr.Dhakiya_backend.entities.question.QuestionRepository;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.AnsweredQuestionRepository;
import com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion.PatchAnsweredQuestion;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.GetQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.SubmitQuizResult;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagRepository;
import com.lvr.Dhakiya_backend.restadvice.exceptions.BadRequestException;
import com.lvr.Dhakiya_backend.restadvice.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizResultService {
  private final QuizResultRepository quizResultRepository;
  private final QuestionRepository questionRepository;
  private final QuizRepository quizRepository;
  private final AnsweredQuestionRepository answeredQuestionRepository;
  private final AnswerRepository answerRepository;
  private final TagRepository tagRepository;

  public GetQuizResult create(PostQuizResult dto) {

    Quiz quiz = quizRepository.findById(dto.quizId()).orElseThrow(NotFoundException::new);
    if (!quiz.getIsFinal()) {
      throw new BadRequestException("Quiz must be saved before using it");
    }
    QuizResult quizResult = new QuizResult(quiz);

    List<Question> questions = questionRepository.findQuestionByQuiz(quiz);
    List<AnsweredQuestion> answeredQuestions = questions.stream().map(this::copyQuestion).toList();

    quizResult.setAnsweredQuestions(answeredQuestions);

    quizResultRepository.save(quizResult);
    return GetQuizResult.from(quizResult);
  }

  public List<GetQuizResult> getAll() {
    return quizResultRepository.findAll().stream()
        .map(result -> GetQuizResult.from(result))
        .toList();
  }

  public GetQuizResult getById(Long id) {
    QuizResult quizResult = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);
    return GetQuizResult.from(quizResult);
  }

  public SubmitQuizResult submit(Long id) {
    QuizResult result = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);
    if (result.getIsCompleted()) {
      throw new BadRequestException("quiz has already been submitted");
    }

    List<AnsweredQuestion> questions = result.getAnsweredQuestions();
    for (AnsweredQuestion question : questions) {
      if (isAnsweredCorrect(question)) {
        result.addPoint();
      }
    }
    result.setIsCompleted(true);
    quizResultRepository.save(result);
    return SubmitQuizResult.from(result);
  }

  public GetQuizResult submitAnswer(Long id, PatchAnsweredQuestion patch) {
    QuizResult result = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);

    List<Answer> answers = answerRepository.findAllById(patch.answerIds());
    if (patch.answerIds().size() != answers.size()) {
      throw new BadRequestException("Invalid Answer Id");
    }

    AnsweredQuestion question =
        answeredQuestionRepository.findById(patch.questionId()).orElseThrow(NotFoundException::new);

    question.setSelectedAnswers(answers);

    quizResultRepository.save(result);
    return GetQuizResult.from(result);
  }

  public void delete(Long id) {
    QuizResult quizResult = quizResultRepository.findById(id).orElseThrow(NotFoundException::new);

    quizResult.getAnsweredQuestions().forEach(answeredQuestion -> removeTagPoint(answeredQuestion));
    quizResultRepository.deleteById(id);
  }

  // Helper methods:
  @Transactional
  public void removeTagPoint(AnsweredQuestion answeredQuestion) {
    if (answeredQuestion.getQuestion().getTag() == null) {
      return;
    }
    Tag tag = answeredQuestion.getQuestion().getTag();
    tag.flagNegative();
    tagRepository.save(tag);
  }

  public AnsweredQuestion copyQuestion(Question question) {
    Integer validAnswerCount = 0;
    for (Answer answer : question.getAnswers()) {
      if (answer.getIsCorrect()) validAnswerCount++;
    }
    AnsweredQuestion copiedQuestion = new AnsweredQuestion(question, validAnswerCount);
    return answeredQuestionRepository.save(copiedQuestion);
  }

  public Boolean isAnsweredCorrect(AnsweredQuestion question) {
    Tag tag = question.getQuestion().getTag();
    if (tag != null) {
      tag.markAsSeen();
    }
    List<Answer> answers = question.getSelectedAnswers();

    if (answers.size() != question.getValidAnswerCount()) {
      return false;
    }

    for (Answer answer : answers) {
      if (!answer.getIsCorrect()) {
        return false;
      }
    }
    if (tag != null) {
      tag.flagPositive();
      tagRepository.save(tag);
    }
    return true;
  }
}
