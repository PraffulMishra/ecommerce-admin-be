package com.maverick.ecommerce.controller;

import com.maverick.ecommerce.model.Category;
import com.maverick.ecommerce.model.Product;
import com.maverick.ecommerce.repository.CategoryRepository;
import com.maverick.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Fetch category based on categoryId
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if (category != null) {
            product.setCategory(category);
            Product createdProduct = productRepository.save(product);
            return ResponseEntity.ok(createdProduct);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}