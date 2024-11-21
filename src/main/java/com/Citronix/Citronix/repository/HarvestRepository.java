package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.model.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Integer> {
    boolean existsByFieldAndSeason(Field field, Season season);
}
