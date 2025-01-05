package com.example.renovationtracker.controller;

import com.example.renovationtracker.model.Contractor;
import com.example.renovationtracker.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contractors")
public class ContractorController {
    @Autowired
    private ContractorService contractorService;

    @PostMapping
    public Contractor createContractor(@RequestBody Contractor contractor) {
        return contractorService.createContractor(contractor);
    }

    @GetMapping
    @Cacheable("contractors")
    public List<Contractor> getAllContractors() {
        return contractorService.getAllContractors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contractor> getContractorById(@PathVariable Long id) {
        return contractorService.getContractorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contractor> updateContractor(@PathVariable Long id, @RequestBody Contractor contractor) {
        return ResponseEntity.ok(contractorService.updateContractor(id, contractor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractor(@PathVariable Long id) {
        contractorService.deleteContractor(id);
        return ResponseEntity.noContent().build();
    }
}
