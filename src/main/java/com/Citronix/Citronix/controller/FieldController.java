package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.FieldDTO;
import com.Citronix.Citronix.exception.EntityInvalidFarmException;
import com.Citronix.Citronix.exception.EntityInvalidFieldArea;
import com.Citronix.Citronix.exception.EntityNotFoundException;
import com.Citronix.Citronix.mapper.FieldMapper;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.service.FarmService;
import com.Citronix.Citronix.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private FieldMapper fieldMapper;

    @PostMapping("/addField")
    public ResponseEntity<?> addField(@Valid @RequestBody FieldDTO fieldDTO, BindingResult result) throws EntityInvalidFarmException, EntityInvalidFieldArea {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Field field = fieldMapper.toEntity(fieldDTO);

            field.setFarm(farmService.getFarmById(fieldDTO.getFarmId()));
            Field createdField = fieldService.addField(field);

            FieldDTO responseDTO = fieldMapper.toDTO(createdField);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{fieldId}/updateField")
    public ResponseEntity<?> updateField(@Valid @RequestBody FieldDTO fieldDTO, BindingResult result, @PathVariable int fieldId) throws EntityNotFoundException {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Field field = fieldMapper.toEntity(fieldDTO);

            field.setFarm(farmService.getFarmById(fieldDTO.getFarmId()));

            Field updatedField = fieldService.updateField(fieldId, field);

            FieldDTO responseDTO = fieldMapper.toDTO(updatedField);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/allFields")
    public ResponseEntity<?> getAllFields(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "1") int size) {
        Page<Field> fields = fieldService.getFields(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(fields);
    }
}
