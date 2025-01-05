package com.example.renovationtracker.service;

import com.example.renovationtracker.model.Budget;
import com.example.renovationtracker.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public Budget updateBudget(Long id, Budget updatedBudget) {
        return budgetRepository.findById(id)
                .map(budget -> {
                    budget.setTotalBudget(updatedBudget.getTotalBudget());
                    budget.setUsedBudget(updatedBudget.getUsedBudget());
                    budget.setRemainingBudget(updatedBudget.getRemainingBudget());
                    return budgetRepository.save(budget);
                })
                .orElseGet(() -> {
                    updatedBudget.setId(id);
                    return budgetRepository.save(updatedBudget);
                });
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
