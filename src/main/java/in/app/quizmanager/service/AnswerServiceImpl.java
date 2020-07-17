package in.app.quizmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.app.quizmanager.model.AnswerDto;
import in.app.quizmanager.model.Option;
import in.app.quizmanager.model.Question;
import in.app.quizmanager.model.Result;

@Service
public class AnswerServiceImpl implements AnswerService {
    
    private static final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);


  @Autowired QuestionService questionService;

  @Override
  public List<Result> checkAnswer(AnswerDto answerDto) {
    List<Result> results = new ArrayList<>();
    Long categoryId = answerDto.getCategoryId();

    answerDto
        .getAnswers()
        .forEach(
            answer -> {
              log.info(answer.toString());
              Question question =
                  questionService.findById(answer.getQuestion().getId()).orElse(null);
              if (question != null) {
                log.info(question.toString());
                Option correctOption =
                    question.getOptions().values().stream()
                        .filter(Option::isCorrect)
                        .findFirst()
                        .orElse(new Option());
                Option selectedOption;
                if (answer.getSelectedOption() != -1) {
                   selectedOption = question.getOptions().get(answer.getSelectedOption());
                }else{
                  selectedOption = new Option();
                }
                Result result = new Result();
                result.setQuestionText(question.getText());
                result.setSelectedAnswer(selectedOption.getText());
                result.setCorrectAnswer(correctOption.getText());
                result.setCorrect(selectedOption.isCorrect());
                results.add(result);
              }
            });
    return results;
  }
}
