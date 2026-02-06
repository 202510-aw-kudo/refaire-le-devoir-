package com.example.refaire.le.devoir.exception;

public class TodoNotFoundException extends RuntimeException {

  public TodoNotFoundException(Long id) {
    super("Todo not found: id=" + id);
  }
}
