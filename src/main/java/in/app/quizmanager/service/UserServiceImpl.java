package in.app.quizmanager.service;

import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.app.quizmanager.model.UserDto;
import in.app.quizmanager.model.Users;
import in.app.quizmanager.repository.UserRepository;
import in.app.quizmanager.util.EncryptionUtil;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;

  @Autowired EncryptionUtil encryptionUtil;

  @Override
  public Users findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public Users loadUserByUsername(String username) throws UsernameNotFoundException {
	  Users user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }
    return user;
  }

  @Override
  public Users saveUser(UserDto userDto) throws GeneralSecurityException {
	  Users user = new Users();
    user.setName(userDto.getName());
    user.setUsername(userDto.getUsername());
    user.setPassword(encryptionUtil.encrypt(userDto.getPassword()));
    user.setAdmin(false);
    user.setLocked(false);
    userRepository.saveAndFlush(user);
    return user;
  }
}
