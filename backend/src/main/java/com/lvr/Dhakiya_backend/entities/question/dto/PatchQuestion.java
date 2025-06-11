package com.lvr.Dhakiya_backend.entities.question.dto;

import com.lvr.Dhakiya_backend.entities.answer.dto.AnswerPatchForQuestion;
import java.util.List;

public record PatchQuestion(
    String question, Integer answerCount, List<AnswerPatchForQuestion> answers, Long tagId) {}
