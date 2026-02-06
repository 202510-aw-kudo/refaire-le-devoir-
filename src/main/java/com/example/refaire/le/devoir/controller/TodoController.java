package com.example.refaire.le.devoir.controller;

import com.example.refaire.le.devoir.entity.Todo;
import com.example.refaire.le.devoir.form.TodoForm;
import com.example.refaire.le.devoir.service.TodoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  // Affiche la page de liste des todos.
  @GetMapping("/todos")
  public String list(Model model) {
    List<Todo> todos = todoService.findAllOrderByCreatedAtDesc();
    model.addAttribute("todos", todos);
    return "todo/list";
  }

  // Affiche le formulaire de creation d'un todo.
  @GetMapping("/todos/new")
  public String newForm(Model model) {
    model.addAttribute("todoForm", new TodoForm());
    return "todo/form";
  }

  // Affiche le detail d'un todo par identifiant.
  @GetMapping("/todos/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    model.addAttribute("todoId", id);
    return "todo/detail";
  }

  // Recoit les donnees du formulaire, enregistre, puis redirige.
  @PostMapping("/todos/confirm")
  public String confirm(
      @ModelAttribute("todoForm") TodoForm form,
      RedirectAttributes redirectAttributes) {
    todoService.create(form);
    redirectAttributes.addFlashAttribute("message", "登録が完了しました");
    return "redirect:/todos";
  }

  // Supprime un todo puis redirige vers la liste.
  @PostMapping("/todos/{id}/delete")
  public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    todoService.deleteById(id);
    redirectAttributes.addFlashAttribute("message", "ToDoを削除しました");
    return "redirect:/todos";
  }

  // Toggle completed (sync)
  @PostMapping("/todos/{id}/toggle")
  public String toggle(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    todoService.toggleCompleted(id);
    redirectAttributes.addFlashAttribute("message", "完了状態を更新しました");
    return "redirect:/todos";
  }

  // Toggle completed (AJAX)
  @PostMapping("/todos/{id}/toggle-ajax")
  public ResponseEntity<Void> toggleAjax(@PathVariable("id") Long id) {
    todoService.toggleCompleted(id);
    return ResponseEntity.ok().build();
  }

  // Affiche le formulaire d'edition d'un todo.
  @GetMapping("/todos/{id}/edit")
  public String edit(@PathVariable("id") Long id, Model model) {
    Todo todo = todoService.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    model.addAttribute("todoId", id);
    model.addAttribute("todoForm", todoService.toForm(todo));
    return "todo/form";
  }
}
