package com.Citronix.Citronix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
public class HarvestDetailDTO {
    private int id;
    @NotNull(message = "La quantité ne peut pas être nulle.")
    @Positive(message = "La quantité doit être supérieure à 0.")
    private double quantity; // kg

    @NotNull(message = "L'identifiant de la récolte ne peut pas être nul.")
    private int harvestId;

    @NotNull(message = "L'identifiant de l'arbre ne peut pas être nul.")
    private int treeId;
}