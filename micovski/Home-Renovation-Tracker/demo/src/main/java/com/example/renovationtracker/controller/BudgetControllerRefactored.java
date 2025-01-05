package com.example.renovationtracker.controller;

import com.example.renovationtracker.model.Budget;
import com.example.renovationtracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class BudgetControllerRefactored extends GenericController<Budget, Long> {
    @Autowired
    private BudgetService budgetService;

    @Override
    protected Budget createEntity(Budget budget) {
        return budgetService.createBudget(budget);
    }

    @Override
    protected List<Budget> getAllEntities() {
        return budgetService.getAllBudgets();
    }

    @Override
    protected Optional<Budget> getEntityById(Long id) {
        return budgetService.getBudgetById(id);
    }

    @Override
    protected Budget updateEntity(Long id, Budget budget) {
        return budgetService.updateBudget(id, budget);
    }

    @Override
    protected void deleteEntity(Long id) {
        budgetService.deleteBudget(id);
    }
}
