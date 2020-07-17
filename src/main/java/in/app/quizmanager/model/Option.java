package in.app.quizmanager.model;

import java.io.Serializable;


public class Option implements Serializable {
  private static final long serialVersionUID = 1;
  private String text;
  private boolean correct;

public String getText() {
    return text;
}

public void setText(String text) {
    this.text = text;
}

public boolean isCorrect() {
    return correct;
}

public void setCorrect(boolean correct) {
    this.correct = correct;
}

public Option(String text, boolean correct) {
    super();
    this.text = text;
    this.correct = correct;
}


public Option() {
}
  
  
}
