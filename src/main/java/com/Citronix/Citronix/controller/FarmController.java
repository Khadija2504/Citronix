package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.FarmDTO;
import com.Citronix.Citronix.mapper.FarmMapper;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/farm")
@Controller
public class FarmController {
    @Autowired
    private FarmService farmService;

    @Autowired
    private FarmMapper farmMapper;

    @PostMapping("/addFarm")
    public ResponseEntity<?> addFarm(@Valid @RequestBody FarmDTO farmDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Farm farm = farmMapper.toEntity(farmDTO);

        Farm savedFarm = farmService.addFarm(farm);
        FarmDTO savedFarmDTO = farmMapper.toDTO(savedFarm);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarmDTO);
    }

    @PutMapping("/{farmId}/updateFarm")
    public ResponseEntity<?> updateFarm(@Valid @RequestBody FarmDTO farmDTO, BindingResult result, @PathVariable int farmId) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Farm farm = farmMapper.toEntity(farmDTO);
        Farm updatedFarm = farmService.updateFarm(farmId, farm);
        FarmDTO updatedFarmDTO = farmMapper.toDTO(updatedFarm);
        return ResponseEntity.status(HttpStatus.OK).body(updatedFarmDTO);
    }

    @GetMapping("/getAllFarms")
    public ResponseEntity<?> getAllFarms(){
        List<Farm> farms = farmService.getAllFarms();
        return ResponseEntity.status(HttpStatus.OK).body(farms);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFarm(@RequestParam String search) {

        List<Farm> farms = farmService.searchFarms(search);

        return farms.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No farms found for " + search)
                : ResponseEntity.status(HttpStatus.OK).body(farms);
    }
}
