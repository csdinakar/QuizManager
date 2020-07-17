package in.app.quizmanager.service;

import java.util.List;

import in.app.quizmanager.model.AnswerDto;
import in.app.quizmanager.model.Result;

public interface AnswerService {
  List<Result> checkAnswer(AnswerDto answerDto);
}
