package com.petclinic.petclinic.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VisitServiceUnitTest {

    private final VisitService visitService;
    @MockitoBean
    private VisitRepository visitRepository;

    @Autowired
    public VisitServiceUnitTest(VisitService visitService) {
        this.visitService = visitService;
    }

    @Test
    public void shouldFindByReferenceNumber() {
        Visit visit = new Visit(1L, "V01-23", LocalDate.of(2026, Month.JANUARY, 20), "Psychogolic care");
        when(visitRepository.findByReferenceNumber("V01-23")).thenReturn(Optional.of(visit));

        Optional<Visit> byReferenceNumber = this.visitService.findByReferenceNumber("V01-23");
        assertThat(byReferenceNumber).contains(visit);

        Visit result = byReferenceNumber.get();
        assertThat(result.purpose).isEqualTo("Psychogolic care");
        assertThat(result.date.getDayOfMonth()).isEqualTo(20);

        verify(visitRepository, times(1)).findByReferenceNumber("V01-23");
    }

    @Test
    public void shouldNotFindByReferenceNumberV02_23() {
        var visit = new Visit(1L, "V01-23", LocalDate.of(2026, Month.JANUARY, 19), "dental care");
        when(visitRepository.findByReferenceNumber("V01-23")).thenReturn(Optional.of(visit));

        Optional<Visit> byReferenceNumber = this.visitService.findByReferenceNumber("V02-23");

        assertThat(byReferenceNumber).isEmpty();

        verify(visitRepository).findByReferenceNumber("V02-23");
    }
}
