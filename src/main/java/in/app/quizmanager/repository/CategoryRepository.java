package in.app.quizmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.app.quizmanager.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
