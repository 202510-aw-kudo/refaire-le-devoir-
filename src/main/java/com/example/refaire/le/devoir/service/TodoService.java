package com.example.refaire.le.devoir.service;

import com.example.refaire.le.devoir.entity.Todo;
import com.example.refaire.le.devoir.form.TodoForm;
import com.example.refaire.le.devoir.repository.TodoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Transactional
  public Todo create(TodoForm form) {
    Todo todo = toEntity(form);
    return todoRepository.save(todo);
  }

  @Transactional(readOnly = true)
  public List<Todo> findAllOrderByCreatedAtDesc() {
    return todoRepository.findAllOrderByCreatedAtDesc();
  }

  private Todo toEntity(TodoForm form) {
    Todo todo = new Todo();
    todo.setTitle(form.getTitle());
    todo.setDescription(form.getDescription());
    todo.setPriority(form.getPriority());
    return todo;
  }
}
