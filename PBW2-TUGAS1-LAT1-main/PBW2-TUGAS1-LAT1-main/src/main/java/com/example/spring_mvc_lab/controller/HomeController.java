package com.example.spring_mvc_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Spring MVC Lab");
        model.addAttribute("description", "Tugas MID - MVC Pattern & Controller Layer");
        return "home";  // → templates/home.html
    }
}