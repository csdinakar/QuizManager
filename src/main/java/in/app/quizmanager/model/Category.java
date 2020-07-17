package in.app.quizmanager.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Category implements Serializable {
  private static final long serialVersionUID = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  @NotNull
  @NotEmpty
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(name.toUpperCase(), category.name.toUpperCase());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name.toUpperCase());
  }


public Long getId() {
    return id;
}


public void setId(Long id) {
    this.id = id;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public Category() {
}

public Category(Long id, @NotNull @NotEmpty String name) {
    super();
    this.id = id;
    this.name = name;
}
 


  
}
