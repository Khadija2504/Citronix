package com.Citronix.Citronix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class FieldDTO {
    private int id;
    @NotNull(message = "La superficie ne peut pas être nulle.")
    @Positive(message = "La superficie doit être supérieure à 0.1 hectare.")
    private double area;

    @NotNull(message = "L'identifiant de la ferme ne peut pas être nul.")
    private int farmId;

    private List<Long> treeIds;
}