package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {

    @Query("SELECT f FROM Farm f WHERE " +
            "f.name LIKE CONCAT('%',:query, '%')" +
            "Or f.location LIKE CONCAT('%', :query, '%')")
    List<Farm> searchFarms(String query);

    List<Farm> findFarmByCreationDate(LocalDate creationDate);

    List<Farm> findFarmByArea(double area);
}
