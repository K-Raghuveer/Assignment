package com.twinleaves.Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BatchResponse {
    private Long batchId;
    private Integer mrp;
    private Integer sp;
    private Integer purchasePrice;
    private Integer availableQuantity;
    private String inwardedOn;

    public BatchResponse(Long batchId, Integer mrp, Integer sp, Integer purchasePrice, Integer availableQuantity, String inwardedOn) {
        this.batchId = batchId;
        this.mrp = mrp;
        this.sp = sp;
        this.purchasePrice = purchasePrice;
        this.availableQuantity = availableQuantity;
        this.inwardedOn = inwardedOn;
    }

    public BatchResponse() {
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

    public String getInwardedOn() {
        return inwardedOn;
    }

    public void setInwardedOn(String inwardedOn) {
        this.inwardedOn = inwardedOn;
    }
}

