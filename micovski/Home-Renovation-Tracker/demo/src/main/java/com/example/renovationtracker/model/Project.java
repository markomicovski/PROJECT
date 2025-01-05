package com.example.renovationtracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    private String status; // primer: Planned, In Progress, Completed
}
