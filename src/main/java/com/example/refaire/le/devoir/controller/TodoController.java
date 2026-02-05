package com.example.refaire.le.devoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoController {

  // Affiche la page de liste des todos.
  @GetMapping("/todos")
  public String list() {
    return "todo/list";
  }

  // Affiche le formulaire de création d'un todo.
  @GetMapping("/todos/new")
  public String newForm() {
    return "todo/form";
  }

  // Affiche le détail d'un todo par identifiant.
  @GetMapping("/todos/{id}")
  public String detail(@PathVariable("id") Long id) {
    return "todo/detail";
  }
}
