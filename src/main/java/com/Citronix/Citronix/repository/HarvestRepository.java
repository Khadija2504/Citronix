package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.model.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HarvestRepository extends JpaRepository<Harvest, Integer> {
    @Query("SELECT COUNT(h) > 0 " +
            "FROM Harvest h WHERE h.field = :field AND h.season = :season AND YEAR(h.harvestDate) = :year")
    boolean existsByFieldAndSeasonAndYear(@Param("field") Field field,
                                          @Param("season") Season season,
                                          @Param("year") int year);
}
