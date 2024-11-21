package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Integer> {
}
