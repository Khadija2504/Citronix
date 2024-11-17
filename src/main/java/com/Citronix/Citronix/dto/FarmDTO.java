package com.Citronix.Citronix.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FarmDTO {
    private int id;

    @NotBlank(message = "Le nom de la ferme ne peut pas être vide.")
    private String name;

    @NotBlank(message = "La localisation ne peut pas être vide.")
    private String location;

    @NotNull(message = "La superficie ne peut pas être nulle.")
    @Positive(message = "La superficie doit être supérieure à 0.")
    private double area;

    @NotNull(message = "La date de création ne peut pas être nulle.")
    private LocalDate creationDate;

    private List<Long> fieldIds;

}