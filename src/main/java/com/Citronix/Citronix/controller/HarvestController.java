package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.HarvestDTO;
import com.Citronix.Citronix.mapper.HarvestMapper;
import com.Citronix.Citronix.model.Harvest;
import com.Citronix.Citronix.service.FieldService;
import com.Citronix.Citronix.service.HarvestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/harvest")
@RestController
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private HarvestMapper harvestMapper;

    @PostMapping("/recordHarvest")
    public ResponseEntity<?> recordHarvest(@Valid @RequestBody HarvestDTO harvestDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Harvest harvest = harvestMapper.toEntity(harvestDTO);
            harvest.setField(fieldService.getField(harvestDTO.getFieldId()));

            Harvest createdHarvest = harvestService.addHarvest(harvest);

            HarvestDTO responseDTO = harvestMapper.toDto(createdHarvest);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{harvestId}/updateHarvest")
    public ResponseEntity<?> updateHarvest(@Valid @RequestBody HarvestDTO harvestDTO, BindingResult result, @PathVariable int harvestId) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Harvest harvest = harvestMapper.toEntity(harvestDTO);
            harvest.setField(fieldService.getField(harvestDTO.getFieldId()));
            Harvest updatedHarvest = harvestService.updateHarvest(harvestId, harvest);

            HarvestDTO responseDTO = harvestMapper.toDto(updatedHarvest);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/displayAllHarvests")
    public ResponseEntity<?> displayAllHarvests(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "1") int size) {
        Page<Harvest> harvests = harvestService.displayAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(harvests);
    }
}
