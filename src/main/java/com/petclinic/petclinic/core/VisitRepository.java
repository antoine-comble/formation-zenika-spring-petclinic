package com.petclinic.petclinic.core;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Repository
public class VisitRepository {

    public Optional<Visit> findByReferenceNumber(String referenceNumber) {
        return Optional.of(new Visit(1L, referenceNumber, LocalDate.of(2026, Month.JANUARY, 19), "dental care"));
    }
}
