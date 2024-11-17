package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Farm;

import java.util.List;

public interface FarmService {
    Farm addFarm(Farm farm);
    Farm updateFarm(int farmId, Farm farm);
    List<Farm> getAllFarms();
}
