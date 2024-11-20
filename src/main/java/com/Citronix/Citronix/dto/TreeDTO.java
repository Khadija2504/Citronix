package com.Citronix.Citronix.dto;

import com.Citronix.Citronix.model.Field;
import com.Citronix.Citronix.model.enums.TreeStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TreeDTO {
    private int id;
    @NotNull(message = "La date de plantation ne peut pas être nulle.")
    @Past(message = "La date de plantation doit être dans le passé.")
    private LocalDate plantingDate;

    private int age;

    private TreeStatus status;

    @NotNull(message = "L'identifiant du champ ne peut pas être nul.")
    private int fieldId;

    private FieldDTO fields;

    private String alert;
}