package in.app.quizmanager.model;

import javax.validation.constraints.NotEmpty;


public class UserDto {
  @NotEmpty  private String name;
  @NotEmpty  private String username;
  @NotEmpty  private String password;
  @NotEmpty  private String confirmpassword;

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getConfirmpassword() {
    return confirmpassword;
}

public void setConfirmpassword(String confirmpassword) {
    this.confirmpassword = confirmpassword;
}
  
  

}
