package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/farm")
@Controller
public class FarmController {
    @Autowired
    private FarmService farmService;

    @PostMapping("/addFarm")
    public ResponseEntity<?> addFarm(@Valid @RequestBody Farm farm, BindingResult result){
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Farm farmSaved = farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmSaved);
    }
}
