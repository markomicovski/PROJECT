package com.example.renovationtracker.service;

import com.example.renovationtracker.model.Contractor;
import com.example.renovationtracker.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {
    @Autowired
    private ContractorRepository contractorRepository;

    public Contractor createContractor(Contractor contractor) {
        return contractorRepository.save(contractor);
    }

    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    public Optional<Contractor> getContractorById(Long id) {
        return contractorRepository.findById(id);
    }

    public Contractor updateContractor(Long id, Contractor updatedContractor) {
        return contractorRepository.findById(id)
                .map(contractor -> {
                    contractor.setName(updatedContractor.getName());
                    contractor.setContactInfo(updatedContractor.getContactInfo());
                    contractor.setPerformanceRating(updatedContractor.getPerformanceRating());
                    return contractorRepository.save(contractor);
                })
                .orElseGet(() -> {
                    updatedContractor.setId(id);
                    return contractorRepository.save(updatedContractor);
                });
    }

    public void deleteContractor(Long id) {
        contractorRepository.deleteById(id);
    }
}
