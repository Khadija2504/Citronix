package com.Citronix.Citronix.util;

import com.Citronix.Citronix.dto.TreeDTO;
import com.Citronix.Citronix.model.Tree;
import com.Citronix.Citronix.model.enums.TreeStatus;

import java.time.LocalDate;
import java.time.Period;

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
        return treeDTO;
    }
}
