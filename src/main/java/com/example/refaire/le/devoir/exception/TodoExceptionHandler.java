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
    redirectAttributes.addFlashAttribute("error", "削除に失敗しました");
    return "redirect:/todos";
  }
}
