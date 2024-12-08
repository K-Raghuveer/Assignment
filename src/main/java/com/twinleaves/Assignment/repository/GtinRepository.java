package com.twinleaves.Assignment.repository;

import com.twinleaves.Assignment.model.Gtin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GtinRepository extends JpaRepository<Gtin, Long> {
    List<Gtin> findByGtin(String gtin);

}

