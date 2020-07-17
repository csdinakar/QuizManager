package in.app.quizmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import in.app.quizmanager.model.Category;
import in.app.quizmanager.model.CategoryDto;
import in.app.quizmanager.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);


  @Autowired CategoryRepository categoryRepository;

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Optional<Category> findById(Long categoryId) {
    return categoryRepository.findById(categoryId);
  }

  @Override
  public void checkCategoryExist(CategoryDto categoryDto, BindingResult bindingResult) {
    List<Category> categories = categoryRepository.findAll();
    categories.stream().filter(category -> {
      return categoryDto.getCategories().contains(category);
    }).collect(Collectors.toList()).forEach(category -> {
      log.info("Non unique category "+category.toString());
      bindingResult.addError(new ObjectError("categoryerror", "Category "+category.getName()+" already present"));
    });
  }

  @Override
  public CategoryDto saveAll(CategoryDto categoryDto) {
    List<Category> categoryList = new ArrayList<>();
    categoryDto.getCategories().forEach(cDto -> {
      Category category = new Category();
      category.setName(cDto.getName().toUpperCase());
      categoryList.add(category);
    });
    log.info("Save all categories called with "+categoryList.toString());
    categoryRepository.saveAll(categoryList);
    return categoryDto;
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.saveAndFlush(category);
  }

  @Override
  public Category update(Category category) {
    Category existingCategory = findById(category.getId()).orElseThrow(()->new IllegalArgumentException("Category not found"));
    existingCategory.setName(category.getName());
    return categoryRepository.saveAndFlush(existingCategory);
  }

  @Override
  public void delete(Long categoryId) {
    categoryRepository.deleteById(categoryId);
  }
}
