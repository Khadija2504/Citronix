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
        return harvestRepository.save(harvest);
    }
}
