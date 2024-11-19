package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.repository.FarmRepository;
import com.Citronix.Citronix.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmRepository farmRepository;

    @Override
    public Farm addFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm getFarmById(int farmId) {
        return farmRepository.findById(farmId).orElseThrow(() -> new RuntimeException("Farm not found"));
    }

    @Override
    public Farm updateFarm(int farmId, Farm farm) {
        Farm farmFind = getFarmById(farmId);
        farmFind.setName(farm.getName());
        farmFind.setArea(farm.getArea());
        farmFind.setLocation(farm.getLocation());
        farmFind.setCreationDate(farm.getCreationDate());
        return farmRepository.save(farmFind);
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public List<Farm> searchFarms(String search) {
        return farmRepository.searchFarms(search);
    }
}
