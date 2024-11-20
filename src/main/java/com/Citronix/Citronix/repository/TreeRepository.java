package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Integer> {
    List<Tree> findByField(Field field);
}
