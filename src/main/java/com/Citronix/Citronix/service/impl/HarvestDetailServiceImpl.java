package com.Citronix.Citronix.service.impl;

import com.Citronix.Citronix.model.HarvestDetail;
import com.Citronix.Citronix.repository.HarvestDetailRepository;
import com.Citronix.Citronix.repository.HarvestRepository;
import com.Citronix.Citronix.service.HarvestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        boolean treeInOtherHarvest = harvestDetailRepository.existsByTreeAndHarvest(harvestDetail.getTree(), harvestDetail.getHarvest());
        if (treeInOtherHarvest) {
            throw new IllegalStateException("the tree already included in another harvest with the same season");
        }
        return harvestDetailRepository.save(harvestDetail);
    }

    @Override
    public HarvestDetail getHarvestDetail(int id) {
        return harvestDetailRepository.findById(id).get();
    }

    @Override
    public HarvestDetail updateHarvestDetail(int id, HarvestDetail harvestDetail) {
        HarvestDetail existingHarvestDetail = getHarvestDetail(id);
        int harvestFieldId = harvestDetail.getHarvest().getField().getId();
        int treeFieldId = harvestDetail.getTree().getField().getId();
        if(harvestFieldId != treeFieldId) {
            throw new IllegalStateException("the tree hasn't the same filed as the harvest");
        }
        existingHarvestDetail.setHarvest(harvestDetail.getHarvest());
        existingHarvestDetail.setTree(harvestDetail.getTree());
        existingHarvestDetail.setQuantity(harvestDetail.getQuantity());
        return harvestDetailRepository.save(existingHarvestDetail);
    }

    @Override
    public Page<HarvestDetail> harvestDetailList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return harvestDetailRepository.findAll(pageable);
    }
}
