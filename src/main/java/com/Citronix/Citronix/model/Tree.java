package com.Citronix.Citronix.model;

import com.Citronix.Citronix.model.enums.TreeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate plantingDate;
//    private int age;
//    private TreeStatus status;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<HarvestDetail> harvestDetails;
}
