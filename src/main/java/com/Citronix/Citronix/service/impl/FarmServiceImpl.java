package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.repository.FarmRepository;
import com.Citronix.Citronix.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmRepository farmRepository;
    public Farm addFarm(Farm farm) {
        return farmRepository.save(farm);
    }
}
