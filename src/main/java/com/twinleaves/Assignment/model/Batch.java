package com.twinleaves.Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    @Column(nullable = false)
    private Integer mrp;

    @Column(nullable = false)
    private Integer sp;

    @Column(nullable = false)
    private Integer purchasePrice;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private LocalDate inwardedOn;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonIgnore
    private Product product;

    public Batch(Long batchId, Integer mrp, Integer sp, Integer purchasePrice, Integer availableQuantity, LocalDate inwardedOn, Product product) {
        this.batchId = batchId;
        this.mrp = mrp;
        this.sp = sp;
        this.purchasePrice = purchasePrice;
        this.availableQuantity = availableQuantity;
        this.inwardedOn = inwardedOn;
        this.product = product;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public LocalDate getInwardedOn() {
        return inwardedOn;
    }

    public void setInwardedOn(LocalDate inwardedOn) {
        this.inwardedOn = inwardedOn;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Batch() {
    }
}

