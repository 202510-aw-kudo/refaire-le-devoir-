package com.example.refaire.le.devoir.service;

import com.example.refaire.le.devoir.entity.Todo;
import com.example.refaire.le.devoir.form.TodoForm;
import com.example.refaire.le.devoir.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
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

  @Transactional
  public void deleteById(Long id) {
    if (!todoRepository.existsById(id)) {
      throw new com.example.refaire.le.devoir.exception.TodoNotFoundException(id);
    }
    todoRepository.deleteById(id);
  }

  @Transactional
  public Todo toggleCompleted(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new com.example.refaire.le.devoir.exception.TodoNotFoundException(id));
    Boolean current = todo.getCompleted();
    todo.setCompleted(current == null ? Boolean.TRUE : !current);
    return todoRepository.save(todo);
  }

  @Transactional(readOnly = true)
  public List<Todo> findAllOrderByCreatedAtDesc() {
    return todoRepository.findAllOrderByCreatedAtDesc();
  }

  @Transactional(readOnly = true)
  public Optional<Todo> findById(Long id) {
    return todoRepository.findById(id);
  }

  public TodoForm toForm(Todo todo) {
    TodoForm form = new TodoForm();
    form.setTitle(todo.getTitle());
    form.setDescription(todo.getDescription());
    form.setPriority(todo.getPriority());
    return form;
  }

  private Todo toEntity(TodoForm form) {
    Todo todo = new Todo();
    todo.setTitle(form.getTitle());
    todo.setDescription(form.getDescription());
    todo.setPriority(form.getPriority());
    return todo;
  }
}
