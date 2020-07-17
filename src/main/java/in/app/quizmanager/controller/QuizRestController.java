package in.app.quizmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.app.quizmanager.model.Answer;
import in.app.quizmanager.model.Category;
import in.app.quizmanager.model.Question;
import in.app.quizmanager.service.CategoryService;
import in.app.quizmanager.service.QuestionService;

/** Utility controller to add new questions quickly :P */
@RestController
public class QuizRestController {
    
    
    private static final Logger log = LoggerFactory.getLogger(QuizRestController.class);


  @Autowired QuestionService questionService;

  @Autowired CategoryService categoryService;

  @GetMapping("/quizquestions")
  public List<Question> getAllQuestionRest(
      @RequestParam(value = "category", required = false) Long categoryId) {
    return questionService.findAll();
  }

  @PostMapping(
      value = "/quizquestions",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Question> saveAllQuestions(@RequestBody List<Question> questions) {
    return questionService.saveAll(questions);
  }

  // Should be included in its own file if more methods are included! SOLID
  @PostMapping(
      value = "/quizquestions/category",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Category saveCategory(@RequestBody Category category) {
    return categoryService.save(category);
  }

  @PostMapping(
      value = "/quizquestions/category/{categoryId}/answer",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void checkAnswer(
      @RequestBody Answer answers, @PathVariable(value = "categoryId") Long categoryId) {
    log.info("Answer List : " + answers);
  }
}
