package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User register(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new IllegalArgumentException("Utilisateur Existe deja");
    }
    return userRepository.save(user);
  }

  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public String login(User user) {
    Optional<User> foundUser = userRepository.findByUsername((user.getUsername()));

    if (foundUser.isPresent() && foundUser.get().getPassword().equals((user.getPassword()))) {
      return "token_généré_pour" + user.getUsername();
    } else {
      throw new IllegalArgumentException("Login MDP Incorrect !!!!!!");
    }
  }

}
