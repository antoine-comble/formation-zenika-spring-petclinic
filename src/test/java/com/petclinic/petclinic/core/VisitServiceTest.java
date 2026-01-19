package com.petclinic.petclinic.core;

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

    @Test
    public void shouldFindByReferenceNumberV01_23() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V01-23");
        assertThat(visit).contains(new Visit(1L, "V01-23", LocalDate.of(2026, Month.JANUARY, 19), "dental care"));
    }

    @Test
    public void shouldFindByReferenceNumberV02_23() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V02-23");
        assertThat(visit).contains(new Visit(1L, "V02-23", LocalDate.of(2026, Month.JANUARY, 19), "dental care"));
    }

    @Test
    public void shouldFindAllVisits() {
        List<Visit> all = visitService.findAll();
        assertThat(all.size()).isEqualTo(0);
    }
}
