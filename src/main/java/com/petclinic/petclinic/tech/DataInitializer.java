package com.petclinic.petclinic.tech;

import com.petclinic.petclinic.core.Owner;
import com.petclinic.petclinic.core.Pet;
import com.petclinic.petclinic.core.Visit;
import com.petclinic.petclinic.core.VisitService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Autowired
    private final VisitService visitService;

    public DataInitializer(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostConstruct
    public void initializeSampleData() {
        final Owner owner = new Owner(null, "Joe", "Doe", 1000);
        final Pet dog = new Pet(null, "dog", "Luna");
        final Pet cat = new Pet(null, "cat", "Fleur");
        owner.setPets(List.of(dog, cat));
        final Visit visit = new Visit(null, "V01-23", LocalDate.of(2026, Month.JANUARY, 20), "dental care");
        visit.setPet(dog);
        visit.setOwner(owner);
        this.visitService.save(visit);

        final Owner owner2 = new Owner(null, "Joe", "Satriani", 10);
        final Pet dog2 = new Pet(null, "dog", "Caramel");
        owner2.setPets(List.of(dog2));
        final Visit visit2 = new Visit(null, "V01-26", LocalDate.of(2026, Month.APRIL, 12), "psychological care");
        visit2.setPet(dog2);
        visit2.setOwner(owner2);
        this.visitService.save(visit2);
    }
}
