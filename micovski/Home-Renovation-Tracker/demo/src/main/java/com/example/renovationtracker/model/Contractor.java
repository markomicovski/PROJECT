package com.example.renovationtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;
    private String performanceRating; // primer: Excellent, Good, Fair, Poor
}
