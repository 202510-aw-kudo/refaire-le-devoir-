package com.example.refaire.le.devoir.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class TodoExceptionHandler {

  @ExceptionHandler(TodoNotFoundException.class)
  public String handleTodoNotFound(
      TodoNotFoundException ex,
      RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("error", "指定されたToDoが見つかりませんでした");
    return "redirect:/todos";
  }
}
