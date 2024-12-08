package com.twinleaves.Assignment.controller;

import com.twinleaves.Assignment.model.Batch;
import com.twinleaves.Assignment.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<?> createBatch(@RequestBody Batch batch) {
        if (batch.getMrp() == null || batch.getSp() == null || batch.getPurchasePrice() == null) {
            return ResponseEntity.badRequest().body("MRP, SP, and purchase price are required");
        }
        if (batch.getAvailableQuantity() == null || batch.getAvailableQuantity() < 0) {
            return ResponseEntity.badRequest().body("Available quantity must be non-negative");
        }
        Batch createdBatch = batchService.saveBatch(batch);
        return ResponseEntity.ok(createdBatch);
    }

    @GetMapping("/positive-batches")
    public ResponseEntity<?> getPositiveQuantityBatches() {
        List<Batch> batches = batchService.getPositiveQuantityBatches();
        if (batches.isEmpty()) {
            return ResponseEntity.ok("No batches with positive available quantity found");
        }
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/negative-or-zero")
    public ResponseEntity<?> getLatestNegativeOrZeroBatches() {
        List<Batch> batches = batchService.getLatestZeroOrNegativeQuantityBatches();
        if (batches.isEmpty()) {
            return ResponseEntity.ok("No batches with zero or negative available quantity found");
        }
        return ResponseEntity.ok(batches);
    }
}

