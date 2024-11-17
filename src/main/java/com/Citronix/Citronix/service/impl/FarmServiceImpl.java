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

    public Farm getFarmById(int farmId) {
        return farmRepository.findById(farmId).orElseThrow(() -> new RuntimeException("Farm not found"));
    }

    public Farm updateFarm(int farmId, Farm farm) {
        Farm farmFind = getFarmById(farmId);
        farmFind.setName(farm.getName());
        farmFind.setArea(farm.getArea());
        farmFind.setLocation(farm.getLocation());
        farmFind.setCreationDate(farm.getCreationDate());
        farmFind.setFields(farm.getFields());
        return farmRepository.save(farmFind);
    }
}
