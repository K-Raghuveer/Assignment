package com.twinleaves.Assignment.controller;

import com.twinleaves.Assignment.dto.BatchResponse;
import com.twinleaves.Assignment.dto.GtinResponse;
import com.twinleaves.Assignment.model.Batch;
import com.twinleaves.Assignment.model.Gtin;
import com.twinleaves.Assignment.model.Product;
import com.twinleaves.Assignment.service.BatchService;
import com.twinleaves.Assignment.service.GtinService;
import com.twinleaves.Assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gtins")
public class GtinController {
    @Autowired
    private GtinService gtinService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BatchService batchService;


    @PostMapping
    public ResponseEntity<?> saveGtin(@RequestParam String gtin, @RequestParam Long productId) {
        // Validate input
        if (gtin == null || gtin.isEmpty()) {
            return ResponseEntity.badRequest().body("GTIN cannot be null or empty");
        }

        // Fetch product
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        // Create and save GTIN
        Gtin newGtin = new Gtin();
        newGtin.setGtin(gtin);
        newGtin.setProduct(product);
        Gtin savedGtin = gtinService.saveGtin(newGtin);

        return ResponseEntity.ok(savedGtin);
    }

    @GetMapping("/{gtin}")
    public ResponseEntity<?> getGtins(@PathVariable String gtin) {
        // Validate input
        if (isNullOrEmpty(gtin)) {
            return ResponseEntity.badRequest().body("GTIN cannot be null or empty");
        }

        // Fetch GTINs
        List<Gtin> gtins = gtinService.findByGtin(gtin);

        // Handle not found case
        if (gtins.isEmpty()) {
            return ResponseEntity.status(404).body("No GTIN found matching: " + gtin);
        }

        // Transform GTINs to response DTOs
        List<GtinResponse> responses = gtins.stream()
                .map(this::convertToGtinResponse)
                .collect(Collectors.toList());

        // Return response
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/positive-batches")
    public ResponseEntity<?> getGtinsWithPositiveBatches() {
        List<Gtin> gtins = gtinService.findAll();
        List<GtinResponse> responses = gtins.stream()
                // Filter GTINs with at least one positive batch
                .filter(g -> g.getProduct().getBatches().stream().anyMatch(b -> b.getAvailableQuantity() > 0))
                .map(g -> new GtinResponse(
                        g.getGtin(),
                        g.getProduct().getBatches().stream()
                                // Filter only positive batches
                                .filter(b -> b.getAvailableQuantity() > 0)
                                .map(this::convertToBatchResponse)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }



    @GetMapping("/latest-non-positive-batches")
    public ResponseEntity<?> getLatestNonPositiveBatches() {
        List<Gtin> gtins = gtinService.findAll();
        List<GtinResponse> responses = gtins.stream()
                .map(g -> {
                    List<BatchResponse> nonPositiveBatches = g.getProduct().getBatches().stream()
                            .filter(b -> b.getAvailableQuantity() <= 0)
                            .sorted(Comparator.comparing(Batch::getInwardedOn).reversed())
                            .limit(1)
                            .map(this::convertToBatchResponse)
                            .collect(Collectors.toList());
                    return new GtinResponse(g.getGtin(), nonPositiveBatches);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


    // Helper method to check if a string is null or empty
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Helper method to convert Gtin to GtinResponse
    private GtinResponse convertToGtinResponse(Gtin gtin) {
        return new GtinResponse(
                gtin.getGtin(),
                gtin.getProduct().getBatches().stream()
                        .map(this::convertToBatchResponse)
                        .collect(Collectors.toList())
        );
    }

    // Helper method to map Batch to BatchResponse
    private BatchResponse convertToBatchResponse(Batch batch) {
        return new BatchResponse(
                batch.getBatchId(),
                batch.getMrp(),
                batch.getSp(),
                batch.getPurchasePrice(),
                batch.getAvailableQuantity(),
                batch.getInwardedOn().toString()
        );
    }



}
