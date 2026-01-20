package com.petclinic.petclinic.core;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OwnerServiceTest {

    @Autowired
    OwnerService ownerService;

    @BeforeEach
    public void setUp() {
        final Owner owner = new Owner(null, "Joe", "Doe", 1000);
        final Pet dog = new Pet(null, "dog", "Luna");
        final Pet cat = new Pet(null, "cat", "Fleur");
        owner.setPets(List.of(dog, cat));
        this.ownerService.save(owner);
    }

    @Test
    @Transactional
    public void shouldFindOwnerByFirstName() {
        Owner owner = ownerService.findByFirstName("Joe");
        assertThat(owner.lastName).isEqualTo("Doe");
    }

    @Test
    @Transactional
    public void shouldHave2PetsForJoe() {
        Owner owner = ownerService.findByFirstName("Joe");
        assertThat(owner.pets.size()).isEqualTo(2);
        assertThat(owner.pets).extracting((Pet::getName)).contains("Luna");
        assertThat(owner.pets).extracting((Pet::getName)).contains("Fleur");
    }

    @Test
    @Transactional
    public void shouldUpdateDogLunaForJoe() {
        final Owner owner = ownerService.findByFirstName("Joe");
        assertThat(owner.pets.size()).isEqualTo(2);
        assertThat(owner.pets).extracting((Pet::getName)).contains("Luna");

        final List<Pet> luna = owner.pets.stream().filter(p -> p.name.equals("Luna")).toList();
        assertThat(luna.size()).isEqualTo(1);

        luna.getFirst().setName("Miro");
        ownerService.save(owner);

        final Owner owner2 = ownerService.findByFirstName("Joe");
        assertThat(owner2.pets).extracting((Pet::getName)).contains("Miro");
    }
}
