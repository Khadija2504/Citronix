package com.Citronix.Citronix.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double area;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    public Field(double area, Farm farm) {
    }
}
