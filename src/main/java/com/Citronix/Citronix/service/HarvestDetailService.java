package com.Citronix.Citronix.service;

import com.Citronix.Citronix.model.HarvestDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HarvestDetailService {
    HarvestDetail recordHarvestDetail(HarvestDetail harvestDetail);
    HarvestDetail updateHarvestDetail(int id, HarvestDetail harvestDetail);
    HarvestDetail getHarvestDetail(int id);
    Page<HarvestDetail> harvestDetailList(int page, int size);
}
