package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.enums.TreeStatus;
import com.Citronix.Citronix.service.FieldService;
import com.Citronix.Citronix.service.TreeService;
import com.Citronix.Citronix.util.CalcTreeAgeUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        treeService.addtree(tree);
        TreeDTO responseTreeDTO = CalcTreeAgeUtil.productivity(tree);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseTreeDTO);
    }

    @GetMapping("/displayAllTrees")
    public ResponseEntity<?> displayAllTrees() {
        List<Tree> trees = treeService.getTrees();
        List<TreeDTO> treeDTOs = trees.stream()
                .map(CalcTreeAgeUtil::productivity)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(treeDTOs);
    }

    @PutMapping("/{treeId}/updateTree")
    public ResponseEntity<?> updateTree(@Valid @RequestBody TreeDTO treeDTO, BindingResult result, @PathVariable int treeId) {
        if (result.hasErrors()) {
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
        }
        try{
            Tree tree = new Tree();
            tree.setPlantingDate(treeDTO.getPlantingDate());
            tree.setField(fieldService.getField(treeDTO.getFieldId()));
            treeService.updateTree(treeId, tree);

            TreeDTO responseTreeDTO = CalcTreeAgeUtil.productivity(tree);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseTreeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{treeId}/deleteTree")
    public ResponseEntity<?> deleteTree(@PathVariable int treeId) {
        treeService.deleteTree(treeId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted tree successfully");
    }

}