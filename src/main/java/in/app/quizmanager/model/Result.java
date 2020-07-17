package in.app.quizmanager.model;

import java.io.Serializable;


public class Result implements Serializable {
  private static final long serialVersionUID = 1;
  private String questionText;
  private String correctAnswer;
  private String selectedAnswer;
  private boolean correct;

public String getQuestionText() {
    return questionText;
}

public void setQuestionText(String questionText) {
    this.questionText = questionText;
}

public String getCorrectAnswer() {
    return correctAnswer;
}

public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
}

public String getSelectedAnswer() {
    return selectedAnswer;
}

public void setSelectedAnswer(String selectedAnswer) {
    this.selectedAnswer = selectedAnswer;
}

public boolean isCorrect() {
    return correct;
}

public void setCorrect(boolean correct) {
    this.correct = correct;
}
  
  
public Result() {
}
}
