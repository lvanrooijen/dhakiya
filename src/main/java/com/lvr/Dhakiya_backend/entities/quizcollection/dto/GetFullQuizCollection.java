package com.lvr.Dhakiya_backend.entities.quizcollection.dto;


import com.lvr.Dhakiya_backend.entities.quiz.Quiz;
import com.lvr.Dhakiya_backend.entities.quiz.dto.GetQuiz;
import com.lvr.Dhakiya_backend.entities.quizcollection.QuizCollection;
import java.util.List;

public record GetFullQuizCollection(Long id, String title, List<GetQuiz> quizList) {
    public static GetFullQuizCollection from (QuizCollection quizCollection, List<Quiz> quizList){
        List<GetQuiz> convertedQuizList = quizList.stream().map(quiz -> GetQuiz.from(quiz)).toList();
        return new GetFullQuizCollection(quizCollection.getId(), quizCollection.getTitle(),  convertedQuizList);
    }
}
