package com.Citronix.Citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaleDTO {
    private int id;
    @NotNull(message = "La date de vente ne peut pas être nulle.")
    private LocalDate saleDate;

    @NotNull(message = "Le prix unitaire ne peut pas être nul.")
    @Positive(message = "Le prix unitaire doit être supérieur à 0.")
    private double unitPrice;

    @NotNull(message = "Le revenu ne peut pas être nul.")
    @Positive(message = "Le revenu doit être supérieur à 0.")
    private double revenue;
    @NotBlank(message = "Le nom du client ne peut pas être vide.")
    private String customer;

    @NotNull(message = "L'identifiant de la récolte ne peut pas être nul.")
    private int harvestId;
}