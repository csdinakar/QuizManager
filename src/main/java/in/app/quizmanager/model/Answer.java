package in.app.quizmanager.model;

import java.io.Serializable;


public class Answer implements Serializable {
  private static final long serialVersionUID = 1;
  
  
  
public Answer() {
}
  

  public Answer(Question question, Long selectedOption) {
    super();
    this.question = question;
    this.selectedOption = selectedOption;
}


private Question question;

  private Long selectedOption;


public Question getQuestion() {
    return question;
}


public void setQuestion(Question question) {
    this.question = question;
}


public Long getSelectedOption() {
    return selectedOption;
}


public void setSelectedOption(Long selectedOption) {
    this.selectedOption = selectedOption;
}
  
  
}
