package com.Citronix.Citronix.util;

import com.Citronix.Citronix.dto.FarmDTO;
import com.Citronix.Citronix.dto.FieldDTO;
import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Farm;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.enums.TreeStatus;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class CalcTreeAgeUtil {
    public static int calcTreeAge(LocalDate plantingDate) {
        LocalDate currentDate = LocalDate.now();
        if (plantingDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("plantingDate cannot be in the future");
        }

        return Period.between(plantingDate, currentDate).getYears();
    }

    public static TreeDTO productivity(Tree tree) {
        TreeDTO treeDTO = new TreeDTO();
        treeDTO.setId(tree.getId());
        treeDTO.setPlantingDate(tree.getPlantingDate());
        treeDTO.setFieldId(tree.getField().getId());

        int age = calcTreeAge(tree.getPlantingDate());
        treeDTO.setAge(age);

        TreeStatus status;
        if (age < 3) {
            status = TreeStatus.YOUNG;
        } else if (age <= 10) {
            status = TreeStatus.MATURE;
        } else {
            status = TreeStatus.OLD;
        }
        treeDTO.setStatus(status);
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setArea(tree.getField().getArea());
        fieldDTO.setId(tree.getField().getId());
        fieldDTO.setFarmId(tree.getField().getFarm().getId());
        FarmDTO farmDTO = new FarmDTO();
        farmDTO.setId(tree.getField().getFarm().getId());
        farmDTO.setName(tree.getField().getFarm().getName());
        farmDTO.setArea(tree.getField().getFarm().getArea());
        farmDTO.setCreationDate(tree.getField().getFarm().getCreationDate());
        farmDTO.setLocation(tree.getField().getFarm().getLocation());
        treeDTO.setFields(fieldDTO);
        fieldDTO.setFarm(farmDTO);

        return treeDTO;
    }

}
