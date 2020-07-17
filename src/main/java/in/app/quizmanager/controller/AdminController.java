package in.app.quizmanager.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.app.quizmanager.model.Category;
import in.app.quizmanager.model.CategoryDto;
import in.app.quizmanager.model.Option;
import in.app.quizmanager.model.Question;
import in.app.quizmanager.service.CategoryService;
import in.app.quizmanager.service.QuestionService;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    
    
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


  @Autowired QuestionService questionService;

  @Autowired CategoryService categoryService;

  @GetMapping({"/questions"})
  public String getHome(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size,
      @RequestParam("category") Optional<Long> category) {
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(8);
    if (category.isPresent()) {
      model.addAttribute(
          "questions", questionService.findAll(category.get(), currentPage - 1, pageSize));
    } else {
      model.addAttribute("questions", questionService.findAll(currentPage - 1, pageSize));
    }
    model.addAttribute("module", "allquestions");
    model.addAttribute("categories", categoryService.findAll());
    return "question_list";
  }



  @GetMapping({"/question"})
  public String getAddNewQuiz(
      @ModelAttribute("question") Question question, Model model, Principal principal) {

    log.info(principal.toString());

    question.getOptions().put(1L, new Option());
    question.getOptions().put(2L, new Option());
    question.getOptions().put(3L, new Option());
    question.getOptions().put(4L, new Option());
    model.addAttribute("question", question);
    model.addAttribute("module", "newquestion");
    model.addAttribute("categories", categoryService.findAll());
    return "question_new";
  }

  @PostMapping("/question")
  public String addNewQuiz(
      @Valid @ModelAttribute("question") Question question,
      BindingResult bindingResult,
      Model model,
      RedirectAttributes redirectAttributes) {
    questionService.checkQuestionExistsForCategory(question, bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("categories", categoryService.findAll());
      return "question_new";
    }
    questionService.save(question);
    redirectAttributes.addFlashAttribute("added", true);
    return "redirect:/question";
  }

  @PostMapping("/question/delete")
  public String deleteQuestion(
      @ModelAttribute(value = "quesToDelete") Question quesToDelete,
      RedirectAttributes redirectAttributes,
      @RequestParam("page") Optional<Integer> page) {
    int currentPage = page.orElse(1);
    questionService.delete(quesToDelete.getId());
    redirectAttributes.addFlashAttribute("questionDeleted", true);
    return "redirect:/questions?page=" + currentPage;
  }

  @GetMapping("/category")
  public String getCategory(Model model) {
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setCategories(new ArrayList<Category>(Collections.singletonList(new Category())));
    model.addAttribute("categoryDto", categoryDto);
    return "category";
  }

  @PostMapping("/category")
  public String postCategory(
      @ModelAttribute @Valid CategoryDto categoryDto,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    // TODO: could be done using annotations may be ?
	  String categoryName = categoryDto.getCategories().get(0).getName();
	log.debug("categoryDto ==>"+categoryDto.getCategories().get(0).getName());  
	if(categoryName==null || "".equals(categoryName.trim()))
	{
		return "redirect:/";
	}
	  
    categoryService.checkCategoryExist(categoryDto, bindingResult);
    if (bindingResult.hasErrors()) {
      return "category";
    }
    categoryService.saveAll(categoryDto);
    redirectAttributes.addFlashAttribute("added", true);
    return "redirect:/category";
  }
}
