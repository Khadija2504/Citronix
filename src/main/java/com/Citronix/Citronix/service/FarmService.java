package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {
    Farm addFarm(Farm farm);
    Farm updateFarm(int farmId, Farm farm);
    Page<Farm> getAllFarms(int page, int size);
    Page<Farm> searchFarms(String search, int page, int size);
    Farm getFarmById(int farmId);
}
