package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.service.FieldService;
import com.Citronix.Citronix.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/tree")
@RestController
public class TreeController {
    @Autowired
    private TreeService treeService;

    @Autowired
    private FieldService fieldService;

    @PostMapping("/addTree")
    public ResponseEntity<?> addTree(@Valid @RequestBody TreeDTO treeDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Tree tree = new Tree();
        tree.setPlantingDate(treeDTO.getPlantingDate());
        tree.setField(fieldService.getField(treeDTO.getFieldId()));
        Tree createdTree = treeService.addtree(tree);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTree);
    }
}
