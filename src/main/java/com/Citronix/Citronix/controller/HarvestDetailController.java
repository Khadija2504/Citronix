package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.HarvestDTO;
import com.Citronix.Citronix.dto.HarvestDetailDTO;
import com.Citronix.Citronix.mapper.HarvestDetailMapper;
import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.model.HarvestDetail;
import com.Citronix.Citronix.service.HarvestDetailService;
import com.Citronix.Citronix.service.HarvestService;
import com.Citronix.Citronix.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/harvestDetail")
public class HarvestDetailController {

    @Autowired
    private HarvestDetailService harvestDetailService;
    @Autowired
    private HarvestDetailMapper harvestDetailMapper;
    @Autowired
    private HarvestService harvestService;
    @Autowired
    private TreeService treeService;

    @PostMapping("/recordHarvestDetail")
    public ResponseEntity<?> recordHarvestDetail(@Valid @RequestBody HarvestDetailDTO harvestDetailDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailDTO);
            harvestDetail.setHarvest(harvestService.getHarvestById(harvestDetailDTO.getHarvestId()));
            harvestDetail.setTree(treeService.getTree(harvestDetailDTO.getTreeId()));
            HarvestDetail createdHarvestDetail  = harvestDetailService.recordHarvestDetail(harvestDetail);
            HarvestDetailDTO responseDTO = harvestDetailMapper.toDto(createdHarvestDetail);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/updateHarvestDetail")
    public ResponseEntity<?> updateHarvestDetail(@Valid @RequestBody HarvestDetailDTO harvestDetailDTO, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailDTO);
            harvestDetail.setHarvest(harvestService.getHarvestById(harvestDetailDTO.getHarvestId()));
            harvestDetail.setTree(treeService.getTree(harvestDetailDTO.getTreeId()));
            HarvestDetail updatedHarvestDetail  = harvestDetailService.updateHarvestDetail(id, harvestDetail);
            HarvestDetailDTO responseDTO = harvestDetailMapper.toDto(updatedHarvestDetail);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/displayAllHarvestDetails")
    public ResponseEntity<?> displayAllHarvestDetails() {
        List<HarvestDetail> harvestDetailList = harvestDetailService.harvestDetailList();
        return ResponseEntity.status(HttpStatus.OK).body(harvestDetailList);
    }
}
