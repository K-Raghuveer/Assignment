package com.twinleaves.Assignment.service;


import com.twinleaves.Assignment.model.Product;
import com.twinleaves.Assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        // Ensure all batches are linked to the product
        if (product.getBatches() != null) {
            product.getBatches().forEach(batch -> batch.setProduct(product));
        }

        // Save product along with its batches
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}


