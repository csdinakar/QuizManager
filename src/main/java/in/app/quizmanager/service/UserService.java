package in.app.quizmanager.service;

import java.security.GeneralSecurityException;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.app.quizmanager.model.UserDto;
import in.app.quizmanager.model.Users;

public interface UserService extends UserDetailsService {
	Users findByUsername(String username);
  Users saveUser(UserDto userDto) throws GeneralSecurityException;
}
