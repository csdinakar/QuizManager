package in.app.quizmanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import in.app.quizmanager.model.Question;
import in.app.quizmanager.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    
    private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);


  @Autowired QuestionRepository questionRepository;

  @Override
  public void checkForErrorInNewQuestion(Question question, BindingResult bindingResult) {
    long correctCount =
        question.getOptions().entrySet().stream()
            .filter(
                integerOptionEntry -> {
                  return integerOptionEntry.getValue().isCorrect();
                })
            .count();
    if (correctCount > 1) {
      bindingResult.addError(new ObjectError("correctCount", "Only one option can be correct"));
    } else if (correctCount < 1) {
      bindingResult.addError(
          new ObjectError("correctCount", "Atleast one option should be correct"));
    }
    checkQuestionExistsForCategory(question, bindingResult);
  }

  @Override
  public void checkQuestionExistsForCategory(Question question, BindingResult bindingResult) {
    List<Question> questionList =
        questionRepository.findAllByCategoryId(question.getCategory().getId());
    boolean questionExists =
        questionList.stream()
            .anyMatch(
                existingQuestion ->
                    existingQuestion.getText().equalsIgnoreCase(question.getText()));
    if (questionExists) {
      bindingResult.addError(
          new ObjectError(
              "questionerror",
              "Question already present in category " + question.getCategory().getName()));
    }
  }

  @Override
  public List<Question> saveAll(List<Question> questions) {
    return questionRepository.saveAll(questions);
  }

  @Override
  public Page<Question> findAll(int pageNumber, int size) {
    Pageable page = PageRequest.of(pageNumber, size);
    return questionRepository.findAll(page);
  }

  @Override
  public List<Question> findAll(Long categoryId) {
    return questionRepository.findAllByCategoryId(categoryId);
  }

  @Override
  public Page<Question> findAll(Long categoryId,int pageNumber, int size) {
    Pageable page = PageRequest.of(pageNumber, size);
    return questionRepository.findAllByCategoryId(categoryId,page);
  }

  @Override
  public List<Question> findAll() {
    return questionRepository.findAll();
  }

  @Override
  public Optional<Question> findById(Long questionId) {
    return questionRepository.findById(questionId);
  }

  @Override
  public Question save(Question question) {
    return questionRepository.saveAndFlush(question);
  }

  @Override
  public Question update(Question question) {
    Question existingQuestion =
        questionRepository
            .findById(question.getId())
            .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    existingQuestion.setText(question.getText());
    existingQuestion.setOptions(question.getOptions());
    existingQuestion.setCategory(question.getCategory());
    return questionRepository.save(existingQuestion);
  }

  @Override
  public void delete(Long questionId) {
    questionRepository.deleteById(questionId);
  }
}
