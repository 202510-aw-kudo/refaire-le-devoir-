package com.example.refaire.le.devoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

  // Affiche le formulaire d'edition d'un todo.
  @GetMapping("/todos/{id}/edit")
  public String edit(@PathVariable("id") Long id, Model model) {
    model.addAttribute("todoId", id);
    return "todo/edit";
  }
}
