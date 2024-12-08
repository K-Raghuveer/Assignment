package com.twinleaves.Assignment.repository;

import com.twinleaves.Assignment.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByAvailableQuantityGreaterThan(Integer quantity);
    List<Batch> findByAvailableQuantityLessThanEqualOrderByInwardedOnDesc(Integer quantity);

}

