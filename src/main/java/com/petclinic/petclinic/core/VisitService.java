package com.petclinic.petclinic.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Optional<Visit> findByReferenceNumber(final String referenceNumber) {
        return visitRepository.findByReferenceNumber(referenceNumber);
    }

    @FindAll
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}
