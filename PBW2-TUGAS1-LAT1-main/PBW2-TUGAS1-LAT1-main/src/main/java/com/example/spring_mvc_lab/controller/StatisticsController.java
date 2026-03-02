package com.example.spring_mvc_lab.controller;

import com.example.spring_mvc_lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    private final ProductService productService;

    // Menggunakan Constructor Injection (lebih disarankan daripada field injection)
    @Autowired
    public StatisticsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/statistics")
    public String showStats(Model model) {
        // Mengirimkan data statistik dari service ke view
        model.addAttribute("title", "Statistik Inventori");
        model.addAttribute("stats", productService.getStatistics());

        return "statistics"; // Mencari file templates/statistics.html
    }
}