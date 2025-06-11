package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.entities.answer.dto.PostAnswer;
import com.lvr.Dhakiya_backend.entities.environment.Environment;
import com.lvr.Dhakiya_backend.entities.environment.EnvironmentService;
import com.lvr.Dhakiya_backend.entities.environment.dto.PostEnvironment;
import com.lvr.Dhakiya_backend.entities.flashcard.FlashcardService;
import com.lvr.Dhakiya_backend.entities.flashcard.dto.PostFlashcard;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeck;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.FlashcardDeckService;
import com.lvr.Dhakiya_backend.entities.flashcarddeck.dto.PostFlashCardDeck;
import com.lvr.Dhakiya_backend.entities.note.NoteService;
import com.lvr.Dhakiya_backend.entities.note.dto.PostNote;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollection;
import com.lvr.Dhakiya_backend.entities.notecollection.NoteCollectionService;
import com.lvr.Dhakiya_backend.entities.question.QuestionService;
import com.lvr.Dhakiya_backend.entities.question.dto.PostQuestion;
import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.QuizRepository;
import com.lvr.Dhakiya_backend.entities.quiz.QuizService;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetQuiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.PostQuiz;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollectionService;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.GetQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizcollection.dto.PostQuizCollection;
import com.lvr.Dhakiya_backend.entities.quizresult.QuizResultService;
import com.lvr.Dhakiya_backend.entities.quizresult.dto.PostQuizResult;
import com.lvr.Dhakiya_backend.entities.tag.Tag;
import com.lvr.Dhakiya_backend.entities.tag.TagService;
import com.lvr.Dhakiya_backend.entities.tag.dto.PostTag;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
  private final EnvironmentService environmentService;
  private final TagService tagService;
  private final NoteCollectionService noteCollectionService;
  private final NoteService noteService;
  private final FlashcardDeckService flashcardDeckService;
  private final FlashcardService flashcardService;
  private final QuizCollectionService quizCollectionService;
  private final QuizService quizService;
  private final QuizRepository quizRepository;
  private final QuestionService questionService;
  private final QuizResultService quizResultService;

  @Override
  public void run(String... args) throws Exception {
    seedEnvironments();
    seedTags();
    seedNotes();
    seedFlashcardDecks();
    seedFlashcards();
    seedQuizCollections();
    seedQuizzes();
    seedQuestions();
    seedQuizResult();
  }

  public void seedEnvironments() {
    environmentService.create(new PostEnvironment("OCP 21"));
    environmentService.create(new PostEnvironment("History"));
    environmentService.create(new PostEnvironment("Lyricology"));
  }

  private void seedTags() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    tagService.create(new PostTag(environments.get(0).getId(), "Functional Programming"));
    tagService.create(new PostTag(environments.get(0).getId(), "Object-Oriented"));
    tagService.create(new PostTag(environments.get(0).getId(), "Design Patterns"));
    tagService.create(new PostTag(environments.get(0).getId(), "Clean Code"));
    tagService.create(new PostTag(environments.get(0).getId(), "Concurrency"));
    tagService.create(new PostTag(environments.get(0).getId(), "Dependency Injection"));
    tagService.create(new PostTag(environments.get(0).getId(), "Software Architecture"));
    tagService.create(new PostTag(environments.get(0).getId(), "Agile Practices"));
    tagService.create(new PostTag(environments.get(0).getId(), "Test-Driven Development"));
    tagService.create(new PostTag(environments.get(0).getId(), "SOLID Principles"));

    tagService.create(new PostTag(environments.get(1).getId(), "Ancient Rome"));
    tagService.create(new PostTag(environments.get(1).getId(), "The Renaissance"));
    tagService.create(new PostTag(environments.get(1).getId(), "World Wars"));
    tagService.create(new PostTag(environments.get(1).getId(), "Cold War"));
    tagService.create(new PostTag(environments.get(1).getId(), "French Revolution"));
    tagService.create(new PostTag(environments.get(1).getId(), "Colonialism"));
    tagService.create(new PostTag(environments.get(1).getId(), "The Industrial Revolution"));
    tagService.create(new PostTag(environments.get(1).getId(), "Historical Figures"));
    tagService.create(new PostTag(environments.get(1).getId(), "Medieval Europe"));
    tagService.create(new PostTag(environments.get(1).getId(), "Prehistoric Times"));

    tagService.create(new PostTag(environments.get(2).getId(), "Rhythm and Meter"));
    tagService.create(new PostTag(environments.get(2).getId(), "Rhyme Schemes"));
    tagService.create(new PostTag(environments.get(2).getId(), "Poetry Forms"));
    tagService.create(new PostTag(environments.get(2).getId(), "Hip-Hop Lyrics"));
    tagService.create(new PostTag(environments.get(2).getId(), "Songwriting Techniques"));
    tagService.create(new PostTag(environments.get(2).getId(), "Narrative Lyrics"));
    tagService.create(new PostTag(environments.get(2).getId(), "Lyric Analysis"));
    tagService.create(new PostTag(environments.get(2).getId(), "Flow and Delivery"));
    tagService.create(new PostTag(environments.get(2).getId(), "Metaphors in Lyrics"));
    tagService.create(new PostTag(environments.get(2).getId(), "Lyricism in Music"));
  }

  private void seedNotes() {
    List<NoteCollection> noteCollections = noteCollectionService.getAll();
    if (noteCollections.size() < 3) return;

    List<Tag> tags = tagService.getAll();
    if (tags.size() < 3) return;

    noteService.create(
        new PostNote(
            noteCollections.get(0).getId(),
            "Understanding Polymorphism",
            "Polymorphism in Object-Oriented Programming allows for methods to operate on objects of different types. This feature is key for creating flexible and reusable code.",
            tags.get(0).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(0).getId(),
            "Concurrency and Parallelism",
            "Concurrency allows multiple tasks to run seemingly simultaneously, while parallelism involves actually running tasks at the same time on multiple cores.",
            tags.get(0).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(0).getId(),
            "Agile Software Development",
            "Agile emphasizes iterative development, customer collaboration, and adaptability to change over strict adherence to processes and tools.",
            tags.get(0).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(1).getId(),
            "The Fall of the Roman Empire",
            "The Roman Empire fell due to a combination of internal weaknesses, invasions by barbarian tribes, and economic instability.",
            tags.get(1).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(1).getId(),
            "The Industrial Revolution",
            "The Industrial Revolution marked a major turning point in history, with advancements in machinery, factory production, and urbanization transforming society.",
            tags.get(1).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(1).getId(),
            "The Cold War",
            "The Cold War was a period of geopolitical tension between the Soviet Union and the United States, influencing global politics from the late 1940s to the early 1990s.",
            tags.get(1).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(2).getId(),
            "The Art of Rhyme Schemes",
            "Rhyme schemes are the pattern of rhymes at the end of each line of a poem or song, and they can create structure, rhythm, and a sense of cohesion.",
            tags.get(2).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(2).getId(),
            "Metaphors in Music",
            "Metaphors in lyrics are used to convey deeper meanings and emotions by comparing one thing to another, enriching the experience of the listener.",
            tags.get(2).getId()));

    noteService.create(
        new PostNote(
            noteCollections.get(2).getId(),
            "Narrative Lyrics and Storytelling",
            "Narrative lyrics tell a story, often following a plot and characters, to engage the listener in an emotional and visual journey.",
            tags.get(2).getId()));
  }

  private void seedFlashcardDecks() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(0).getId(), "Access Modifiers"));
    flashcardDeckService.create(new PostFlashCardDeck(environments.get(1).getId(), "World war 1"));
    flashcardDeckService.create(
        new PostFlashCardDeck(environments.get(2).getId(), "Rhyme schemes"));
  }

  private void seedFlashcards() {
    List<FlashcardDeck> decks = flashcardDeckService.getAll();
    if (decks.size() < 3) return;

    List<Tag> tags = tagService.getAll();
    if (tags.size() < 3) return;

    flashcardService.create(new PostFlashcard(decks.get(0).getId(), null, "ABC", "DEF", null));
    flashcardService.create(
        new PostFlashcard(decks.get(1).getId(), tags.get(0).getId(), "ABC", "DEF", 10));
    flashcardService.create(
        new PostFlashcard(decks.get(2).getId(), tags.get(1).getId(), "ABC", "DEF", 8));
  }

  private void seedQuizCollections() {
    List<Environment> environments = environmentService.getAll();
    if (environments.size() < 3) return;

    quizCollectionService.create(
        new PostQuizCollection(environments.get(0).getId(), "My First Quiz Collection"));
    quizCollectionService.create(
        new PostQuizCollection(environments.get(1).getId(), "My Second Quiz Collection"));
    quizCollectionService.create(
        new PostQuizCollection(environments.get(2).getId(), "My Third Quiz Collection"));
  }

  private void seedQuizzes() {
    List<GetQuizCollection> quizCollections = quizCollectionService.getAll();
    if (quizCollections.size() < 3) return;

    quizService.create(new PostQuiz(quizCollections.get(0).id(), "My First Quiz", null));
    quizService.create(new PostQuiz(quizCollections.get(1).id(), "My Second Quiz", 21));
    quizService.create(new PostQuiz(quizCollections.get(2).id(), "My Third Quiz", 30));
  }

  private void seedQuestions() {
    List<GetQuiz> quizzes = quizService.getAll();
    if (quizzes.size() < 3) return;
    List<PostAnswer> answers =
        new ArrayList<>(
            List.of(
                new PostAnswer("Answer a", true),
                new PostAnswer("Answer b", true),
                new PostAnswer("Answer c", true),
                new PostAnswer("Answer d", false)));

    for (int i = 0; i < 3; i++) {
      List<PostQuestion> questions =
          List.of(
              new PostQuestion(quizzes.get(i).id(), "Question 1?", 4, answers, 1L),
              new PostQuestion(quizzes.get(i).id(), "Question 2?", 4, answers, 1L),
              new PostQuestion(quizzes.get(i).id(), "Question 3?", 4, answers, null),
              new PostQuestion(quizzes.get(i).id(), "Question 4?", 4, answers, 1L),
              new PostQuestion(quizzes.get(i).id(), "Question 5?", 4, answers, null),
              new PostQuestion(quizzes.get(i).id(), "Question 6?", 4, answers, 1L),
              new PostQuestion(quizzes.get(i).id(), "Question 7?", 4, answers, null),
              new PostQuestion(quizzes.get(i).id(), "Question 8?", 4, answers, 1L),
              new PostQuestion(quizzes.get(i).id(), "Question 9?", 4, answers, 2L),
              new PostQuestion(quizzes.get(i).id(), "Question 10?", 4, answers, 2L),
              new PostQuestion(quizzes.get(i).id(), "Question 11?", 4, answers, 2L),
              new PostQuestion(quizzes.get(i).id(), "Question 12?", 4, answers, 2L));

      for (PostQuestion question : questions) {
        questionService.create(question);
      }
    }
  }

  private void seedQuizResult() {
    List<Quiz> quizzes = quizRepository.findAll();
    if (quizzes.size() < 3) return;

    Quiz quiz = quizzes.get(0);
    quiz.setIsFinal(true);
    quizRepository.save(quiz);
    quizResultService.create(new PostQuizResult(quiz.getId()));
  }
}
