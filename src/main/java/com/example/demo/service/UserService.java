package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  public User register(User user) {
    logger.debug("Tentative d'enregistrement de l'utilisateur : {}", user.getUsername());
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new IllegalArgumentException("Utilisateur Existe deja");
    }
    User savedUser = userRepository.save(user);
    logger.info(
        "--------------------------------------------------------------------------------------------Utilisateur enregistré avec succès : {}",
        savedUser.getUsername());
    return savedUser;
    // return userRepository.save(user);
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
