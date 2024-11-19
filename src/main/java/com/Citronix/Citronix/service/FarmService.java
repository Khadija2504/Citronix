package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Farm;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {
    Farm addFarm(Farm farm);
    Farm updateFarm(int farmId, Farm farm);
    List<Farm> getAllFarms();
    List<Farm> searchFarms(String search);
    Farm getFarmById(int farmId);
    List<Farm> getFarmsByDate(LocalDate date);
    List<Farm> getFarmsByArea(double area);
}
