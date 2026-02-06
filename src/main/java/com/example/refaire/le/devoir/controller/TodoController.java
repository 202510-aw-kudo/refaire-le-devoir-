package com.example.refaire.le.devoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

  // Affiche la page de liste des todos.
  @GetMapping("/todos")
  public String list() {
    return "todo/list";
  }

  // Affiche le formulaire de creation d'un todo.
  @GetMapping("/todos/new")
  public String newForm() {
    return "todo/form";
  }

  // Affiche le detail d'un todo par identifiant.
  @GetMapping("/todos/{id}")
  public String detail(@PathVariable("id") Long id, Model model) {
    model.addAttribute("todoId", id);
    return "todo/detail";
  }

  // Recoit les donnees du formulaire et les affiche sur la page de detail.
  @PostMapping("/todos/confirm")
  public String confirm(
      @RequestParam("title") String title,
      @RequestParam("description") String description,
      @RequestParam("priority") String priority,
      Model model) {
    model.addAttribute("title", title);
    model.addAttribute("description", description);
    model.addAttribute("priority", priority);
    return "todo/detail";
  }

  // Affiche le formulaire d'edition d'un todo.
  @GetMapping("/todos/{id}/edit")
  public String edit(@PathVariable("id") Long id, Model model) {
    model.addAttribute("todoId", id);
    return "todo/edit";
  }
}
