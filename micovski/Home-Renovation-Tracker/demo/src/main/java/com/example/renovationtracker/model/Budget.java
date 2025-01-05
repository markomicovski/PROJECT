package com.example.renovationtracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalBudget;
    private double usedBudget;
    private double remainingBudget;

    @PrePersist
    @PreUpdate
    private void calculateRemainingBudget() {
        this.remainingBudget = this.totalBudget - this.usedBudget;
    }
}
