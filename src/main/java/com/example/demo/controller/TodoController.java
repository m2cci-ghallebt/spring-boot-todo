package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

  @Autowired
  private TodoRepository todoRepository;

  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    return todoRepository.save(todo);
  }
}
