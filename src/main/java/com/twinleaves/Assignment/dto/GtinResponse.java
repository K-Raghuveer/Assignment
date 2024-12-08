package com.twinleaves.Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class GtinResponse {
    private String gtin;
    private List<BatchResponse> batches;

    public GtinResponse(String gtin, List<BatchResponse> batches) {
        this.gtin = gtin;
        this.batches = batches;
    }

    public GtinResponse() {
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public List<BatchResponse> getBatches() {
        return batches;
    }

    public void setBatches(List<BatchResponse> batches) {
        this.batches = batches;
    }
}

