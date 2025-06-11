package com.lvr.Dhakiya_backend.entities.quizresult.AnsweredQuestion;

import java.util.List;

public record PatchAnsweredQuestion(Long questionId, List<Long> answerIds) {}
