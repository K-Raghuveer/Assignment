package com.twinleaves.Assignment.repository;

import com.twinleaves.Assignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.batches WHERE p.productId = :productId")
    Optional<Product> findByIdWithBatches(@Param("productId") Long productId);


}
