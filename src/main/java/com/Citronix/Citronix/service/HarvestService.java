package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Harvest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HarvestService {
    Harvest addHarvest(Harvest harvest);
    Harvest updateHarvest(int Id, Harvest harvest);
    Harvest getHarvestById(int id);
    Page<Harvest> displayAll(int page, int size);
}
