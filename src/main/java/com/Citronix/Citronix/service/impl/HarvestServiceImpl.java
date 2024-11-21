package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.repository.HarvestRepository;
import com.Citronix.Citronix.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvestServiceImpl implements HarvestService {
    @Autowired
    private HarvestRepository harvestRepository;
    @Override
    public Harvest addHarvest(Harvest harvest) {
        int year = harvest.getHarvestDate().getYear();
        boolean exists = harvestRepository.existsByFieldAndSeasonAndYear(harvest.getField(), harvest.getSeason(), year);
        if (exists) {
            throw new IllegalStateException("a harvest for this field and season already exist");
        }
        return harvestRepository.save(harvest);
    }
}
