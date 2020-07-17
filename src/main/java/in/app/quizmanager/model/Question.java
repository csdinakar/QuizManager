package in.app.quizmanager.model;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import in.app.quizmanager.util.OptionAttributeConverter;

@Entity
@Table(name = "question")
public class Question implements Serializable {
  private static final long serialVersionUID = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true,length = 40000)
  @NotNull
  @NotEmpty
  private String text;

  @Convert(converter = OptionAttributeConverter.class)
  @Column(length = 40000)
  private ConcurrentHashMap<Long, Option> options = new ConcurrentHashMap<>(4);

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public String getText() {
    return text;
}


public void setText(String text) {
    this.text = text;
}


public ConcurrentHashMap<Long, Option> getOptions() {
    return options;
}


public void setOptions(ConcurrentHashMap<Long, Option> options) {
    this.options = options;
}


public Category getCategory() {
    return category;
}


public void setCategory(Category category) {
    this.category = category;
}


public Question() {
}
  
  
}
