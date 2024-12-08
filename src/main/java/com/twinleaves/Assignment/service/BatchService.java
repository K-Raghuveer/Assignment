package com.twinleaves.Assignment.service;

import com.twinleaves.Assignment.model.Batch;
import com.twinleaves.Assignment.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {
    @Autowired
    private BatchRepository batchRepository;

    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    public List<Batch> getPositiveQuantityBatches() {
        return batchRepository.findByAvailableQuantityGreaterThan(0);
    }

    public List<Batch> getLatestZeroOrNegativeQuantityBatches() {
        return batchRepository.findByAvailableQuantityLessThanEqualOrderByInwardedOnDesc(0);
    }
}
