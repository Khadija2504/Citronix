package com.Citronix.Citronix.dto;


import com.Citronix.Citronix.model.enums.Season;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HarvestDTO {
    private int id;

    @NotNull(message = "La date de récolte ne peut pas être nulle.")
    private LocalDate harvestDate;

    @NotNull(message = "La quantité totale ne peut pas être nulle.")
    @Positive(message = "La quantité totale doit être supérieure à 0.")
    private double totalQuantity; // kg

    @NotBlank(message = "La saison ne peut pas être vide.")
    private String season;

    @NotNull(message = "L'identifiant du champ ne peut pas être nul.")
    private int fieldId;

    private List<Long> detailIds;
}