package in.app.quizmanager.util;

import java.util.ArrayList;
import java.util.List;

import in.app.quizmanager.model.Answer;
import in.app.quizmanager.model.Question;

public class AnswerUtility {

  public static List<Answer> createAnswerList(List<Question> questionList) {
    List<Answer> answerList = new ArrayList<>(questionList.size());
    questionList.forEach(
        question -> {
          answerList.add(new Answer(question, -1L));
        });
    return answerList;
  }
}
