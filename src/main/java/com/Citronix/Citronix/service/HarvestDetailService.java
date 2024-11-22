package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.HarvestDetail;

import java.util.List;

public interface HarvestDetailService {
    HarvestDetail recordHarvestDetail(HarvestDetail harvestDetail);
    HarvestDetail updateHarvestDetail(int id, HarvestDetail harvestDetail);
    HarvestDetail getHarvestDetail(int id);
    List<HarvestDetail> harvestDetailList();
}
