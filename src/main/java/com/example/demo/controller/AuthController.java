package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    userService.register(user);
    return ResponseEntity.ok("Utilisateur enregistré");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    String token = userService.login(user);
    return ResponseEntity.ok(token);
  }
}
