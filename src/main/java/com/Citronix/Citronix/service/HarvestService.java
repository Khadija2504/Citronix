package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Harvest;

import java.util.List;

public interface HarvestService {
    Harvest addHarvest(Harvest harvest);
    Harvest updateHarvest(int Id, Harvest harvest);
    Harvest getHarvestById(int id);
    List<Harvest> displayAll();
}
