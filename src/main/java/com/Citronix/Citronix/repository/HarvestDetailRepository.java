package com.Citronix.Citronix.repository;

import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.model.HarvestDetail;
import com.Citronix.Citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Integer> {
    boolean existsByTreeAndHarvest(Tree tree, Harvest harvest);
}
