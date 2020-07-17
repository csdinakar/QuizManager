package in.app.quizmanager.model;

import java.io.Serializable;
import java.util.List;


public class AnswerDto implements Serializable {
  private static final long serialVersionUID = 1;

  List<Answer> answers;

  Long categoryId;
  
  
public AnswerDto() {
}


public List<Answer> getAnswers() {
    return answers;
}


public void setAnswers(List<Answer> answers) {
    this.answers = answers;
}


public Long getCategoryId() {
    return categoryId;
}


public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
}


public AnswerDto(List<Answer> answers, Long categoryId) {
    super();
    this.answers = answers;
    this.categoryId = categoryId;
}
  

}
