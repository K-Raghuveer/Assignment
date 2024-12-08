package com.twinleaves.Assignment.controller;


import com.twinleaves.Assignment.model.Product;
import com.twinleaves.Assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name cannot be null or empty");
        }
        Product createdProduct = productService.saveProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(404).body("Product not found with ID: " + id);
        }
        return ResponseEntity.ok(product.get());
    }
}

