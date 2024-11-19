package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.FarmDTO;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/farm")
@Controller
public class FarmController {
    @Autowired
    private FarmService farmService;

    @PostMapping("/addFarm")
    public ResponseEntity<?> addFarm(@Valid @RequestBody FarmDTO farmDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Farm farm = new Farm();
        farm.setName(farmDTO.getName());
        farm.setArea(farmDTO.getArea());
        farm.setLocation(farmDTO.getLocation());
        farm.setCreationDate(farmDTO.getCreationDate());

        Farm farmSaved = farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmSaved);
    }

    @PutMapping("/{farmId}/updateFarm")
    public ResponseEntity<?> updateFarm(@Valid @RequestBody FarmDTO farmDTO, BindingResult result, @PathVariable int farmId){
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Farm farm = new Farm();
        farm.setName(farmDTO.getName());
        farm.setArea(farmDTO.getArea());
        farm.setLocation(farmDTO.getLocation());
        farm.setCreationDate(farmDTO.getCreationDate());

        Farm farmUpdated = farmService.updateFarm(farmId, farm);
        return ResponseEntity.status(HttpStatus.OK).body(farmUpdated);
    }

    @GetMapping("/getAllFarms")
    public ResponseEntity<?> getAllFarms(){
        List<Farm> farms = farmService.getAllFarms();
        return ResponseEntity.status(HttpStatus.OK).body(farms);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFarm(@RequestParam String search) {
        List<Farm> farms;

        try {
            double value = Double.parseDouble(search);
            farms = farmService.getFarmsByArea(value);
        } catch (NumberFormatException e1) {
            try {
                LocalDate date = LocalDate.parse(search, DateTimeFormatter.ISO_DATE);
                farms = farmService.getFarmsByDate(date);
            } catch (DateTimeParseException e2) {
                farms = farmService.searchFarms(search);
            }
        }

        return farms.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No farms found for " + search)
                : ResponseEntity.status(HttpStatus.OK).body(farms);
    }
}
