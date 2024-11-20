package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.FieldDTO;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.service.FarmService;
import com.Citronix.Citronix.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/field")
@RestController
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FarmService farmService;

    @PostMapping("/addField")
    public ResponseEntity<?> addField(@Valid @RequestBody FieldDTO fieldDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Field field = new Field();
            field.setArea(fieldDTO.getArea());
            field.setFarm(farmService.getFarmById(fieldDTO.getFarmId()));

            Field createdField = fieldService.addField(field);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    @PutMapping("/{fieldId}/updateField")
    public ResponseEntity<?> updateField(@Valid @RequestBody FieldDTO fieldDTO, BindingResult result, @PathVariable int fieldId) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Field field = new Field();
        field.setArea(fieldDTO.getArea());
        field.setFarm(farmService.getFarmById(fieldDTO.getFarmId()));
        Field updatedField = fieldService.updateField(fieldId, field);
        return ResponseEntity.status(HttpStatus.OK).body(updatedField);
    }

    @GetMapping("/allFields")
    public ResponseEntity<?> getAllFields() {
        List<Field> fields = fieldService.getFields();
        return ResponseEntity.status(HttpStatus.OK).body(fields);
    }
}
