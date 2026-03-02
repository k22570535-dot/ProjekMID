package com.example.spring_mvc_lab.service;

import com.example.spring_mvc_lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // Data dummy — nanti akan diganti database di Week JPA
        products.add(new Product(1L, "Laptop ASUS", "Elektronik", 12_500_000, 15));
        products.add(new Product(2L, "Mouse Logitech", "Elektronik", 350_000, 50));
        products.add(new Product(3L, "Buku Java Programming", "Buku", 150_000, 30));
        products.add(new Product(4L, "Kopi Arabica 250g", "Makanan", 85_000, 100));
        products.add(new Product(5L, "Headphone Sony", "Elektronik", 1_200_000, 20));
        products.add(new Product(6L, "Novel Laskar Pelangi", "Buku", 75_000, 45));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Product> findByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Product> search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }
    public List<String> getAllCategories() {
        return products.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }
    public List<Product> getAllProducts() {
        return products;
    }
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Total semua produk
        stats.put("totalProducts", products.size());

        // Total produk per kategori
        Map<String, Long> categorySummary = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        stats.put("categorySummary", categorySummary);

        // Produk termahal
        Product expensive = products.stream()
                .max(Comparator.comparing(Product::getPrice)).orElse(null);
        stats.put("expensive", expensive);

        // Produk termurah
        Product cheapest = products.stream()
                .min(Comparator.comparing(Product::getPrice)).orElse(null);
        stats.put("cheapest", cheapest);

        // Rata-rata harga
        double avgPrice = products.stream()
                .mapToDouble(Product::getPrice).average().orElse(0.0);
        stats.put("avgPrice", avgPrice);

        // Jumlah produk dengan stok < 20
        long lowStockCount = products.stream()
                .filter(p -> p.getStock() < 20).count();
        stats.put("lowStockCount", lowStockCount);

        return stats;
    }

}