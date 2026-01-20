package com.petclinic.petclinic.core;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VisitServiceTest {

    @Autowired
    VisitService visitService;

    @BeforeEach
    public void setUp() {
        var visit = new Visit(null, "V01-23", LocalDate.of(2026, Month.JANUARY, 20), "dental care");
        this.visitService.save(visit);
    }

    @Test @Transactional
    public void shouldFindByReferenceNumberV01_23() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V01-23");
        Visit v = visit.orElse(null);
        assertThat(v).isNotNull();

        assertThat(v.id).isNotNull();
        assertThat(v.date.getDayOfMonth()).isEqualTo(20);
        assertThat(v.purpose).isEqualTo("dental care");
    }

    @Test @Transactional
    public void shouldNotFindByReferenceNumberV02_23() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V02-23");
        assertThat(visit).isEmpty();
    }

    @Test @Transactional
    public void shouldFindAllVisits() {
        List<Visit> all = visitService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

}
