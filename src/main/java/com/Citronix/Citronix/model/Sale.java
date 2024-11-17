package com.Citronix.Citronix.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate saleDate;
    private double unitPrice;
    private double revenue;
    private String customer;

    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    public void calculateRevenue(double quantity) {
        this.revenue = quantity * this.unitPrice;
    }
}
