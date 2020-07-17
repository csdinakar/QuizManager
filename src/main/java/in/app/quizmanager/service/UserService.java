package in.app.quizmanager.service;

import java.security.GeneralSecurityException;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.app.quizmanager.model.User;
import in.app.quizmanager.model.UserDto;

public interface UserService extends UserDetailsService {
  User findByUsername(String username);
  User saveUser(UserDto userDto) throws GeneralSecurityException;
}
