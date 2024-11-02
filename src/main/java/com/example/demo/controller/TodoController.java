package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// import java.util.ArrayList;
import java.util.Optional;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/todos")
public class TodoController {

  @Autowired
  private TodoRepository todoRepository;

  // private List<Todo> todoList = new ArrayList<>();

  @GetMapping
  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
    // return todoList;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
    Optional<Todo> todo = todoRepository.findById(id);
    return todo.map(ResponseEntity::ok).orElse((ResponseEntity.notFound().build()));
  }

  // private static final Logger logger =
  // LoggerFactory.getLogger(TodoController.class);

  // private long nextId = 1;

  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    // todo.setId((long) (nextId++));
    // todoList.add(todo);
    // logger.info("New Todo created: {}", todo);
    // return todo;
    return todoRepository.save(todo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo updatedTodo) {
    return todoRepository.findById(id).map(todo -> {
      todo.setTitle((updatedTodo.getTitle()));
      return ResponseEntity.ok(todoRepository.save(todo));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {

    if (todoRepository.existsById(id)) {
      todoRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}