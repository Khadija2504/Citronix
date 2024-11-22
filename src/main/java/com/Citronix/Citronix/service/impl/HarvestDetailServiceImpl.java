package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.HarvestDetail;
import com.Citronix.Citronix.repository.HarvestDetailRepository;
import com.Citronix.Citronix.repository.HarvestRepository;
import com.Citronix.Citronix.service.HarvestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService {

    @Autowired
    private HarvestDetailRepository harvestDetailRepository;

    @Override
    public HarvestDetail recordHarvestDetail(HarvestDetail harvestDetail) {
        int harvestFieldId = harvestDetail.getHarvest().getField().getId();
        int treeFieldId = harvestDetail.getTree().getField().getId();
        if(harvestFieldId != treeFieldId) {
            throw new IllegalStateException("the tree hasn't the same filed as the harvest");
        }
        return harvestDetailRepository.save(harvestDetail);
    }

    public HarvestDetail getHarvestDetailById(int id) {
        return harvestDetailRepository.findById(id).get();
    }
}
