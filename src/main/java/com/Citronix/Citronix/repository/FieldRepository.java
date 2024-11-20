package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    List<Field> findByFarm(Farm farm);
}
