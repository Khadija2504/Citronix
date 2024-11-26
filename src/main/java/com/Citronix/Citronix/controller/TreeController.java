package com.Citronix.Citronix.controller;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.exception.EntityNotFoundException;
import com.Citronix.Citronix.mapper.TreeMapper;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.enums.TreeStatus;
import com.Citronix.Citronix.service.FieldService;
import com.Citronix.Citronix.service.TreeService;
import com.Citronix.Citronix.util.CalcTreeAgeUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private TreeMapper treeMapper;

    @PostMapping("/addTree")
    public ResponseEntity<?> addTree(@Valid @RequestBody TreeDTO treeDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Tree tree = treeMapper.toEntity(treeDTO);
            tree.setField(fieldService.getField(treeDTO.getFieldId()));
            Tree createdTree = treeService.addtree(tree);
            TreeDTO responseTreeDTO = CalcTreeAgeUtil.productivity(createdTree);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseTreeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/displayAllTrees")
    public ResponseEntity<?> displayAllTrees(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "1") int size) {
        Page<Tree> trees = treeService.getTrees(page, size);
        List<TreeDTO> treeDTOs = trees.stream()
                .map(CalcTreeAgeUtil::productivity)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(treeDTOs);
    }

    @PutMapping("/{treeId}/updateTree")
    public ResponseEntity<?> updateTree(@Valid @RequestBody TreeDTO treeDTO, BindingResult result, @PathVariable int treeId) throws EntityNotFoundException {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Tree tree = treeMapper.toEntity(treeDTO);
            tree.setField(fieldService.getField(treeDTO.getFieldId()));
            Tree updatedTree = treeService.updateTree(treeId, tree);
            TreeDTO responseTreeDTO = CalcTreeAgeUtil.productivity(updatedTree);
            return ResponseEntity.status(HttpStatus.OK).body(responseTreeDTO);
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
