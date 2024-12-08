package com.twinleaves.Assignment.service;

import com.twinleaves.Assignment.model.Gtin;
import com.twinleaves.Assignment.repository.GtinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GtinService {
    @Autowired
    private GtinRepository gtinRepository;

    public Gtin saveGtin(Gtin gtin) {
        return gtinRepository.save(gtin);
    }

    public List<Gtin> findByGtin(String gtin) {
        return gtinRepository.findByGtin(gtin);
    }

    public List<Gtin> findAll() {

        return gtinRepository.findAll();
    }
}
