package com.example.renovationtracker.controller;

import com.example.renovationtracker.model.Contractor;
import com.example.renovationtracker.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contractors")
public class ContractorControllerRefactored extends GenericController<Contractor, Long> {
    @Autowired
    private ContractorService contractorService;

    @Override
    protected Contractor createEntity(Contractor contractor) {
        return contractorService.createContractor(contractor);
    }

    @Override
    protected List<Contractor> getAllEntities() {
        return contractorService.getAllContractors();
    }

    @Override
    protected Optional<Contractor> getEntityById(Long id) {
        return contractorService.getContractorById(id);
    }

    @Override
    protected Contractor updateEntity(Long id, Contractor contractor) {
        return contractorService.updateContractor(id, contractor);
    }

    @Override
    protected void deleteEntity(Long id) {
        contractorService.deleteContractor(id);
    }
}
