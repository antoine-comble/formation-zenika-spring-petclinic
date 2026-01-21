package com.petclinic.petclinic.core;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        final List<Owner> actualOwners = ownerService.findByFirstName("Joe");
        final Owner owner = actualOwners.getFirst();
        assertThat(owner).isNotNull();
        assertThat(owner.lastName).isEqualTo("Doe");
    }

    @Test
    @Transactional
    public void shouldNotFindOwnerByFirstNameJack() {
        final List<Owner> actualOwners = ownerService.findByFirstName("Jack");
        assertThat(actualOwners).isEmpty();
    }

    @Test
    @Transactional
    public void shouldHave2PetsForJoe() {
        final List<Owner> actualOwners = ownerService.findByFirstName("Joe");
        final Owner owner = actualOwners.getFirst();
        assertThat(owner).isNotNull();
        assertThat(owner.pets.size()).isEqualTo(2);
        assertThat(owner.pets).extracting((Pet::getName)).contains("Luna");
        assertThat(owner.pets).extracting((Pet::getName)).contains("Fleur");
    }

    @Test
    @Transactional
    public void shouldUpdateDogLunaForJoe() {
        final List<Owner> actualOwners = ownerService.findByFirstName("Joe");
        final Owner owner = actualOwners.getFirst();
        assertThat(owner).isNotNull();
        assertThat(owner.pets.size()).isEqualTo(2);
        assertThat(owner.pets).extracting((Pet::getName)).contains("Luna");

        final List<Pet> luna = owner.pets.stream().filter(p -> p.name.equals("Luna")).toList();
        assertThat(luna.size()).isEqualTo(1);

        luna.getFirst().setName("Miro");
        ownerService.save(owner);

        final List<Owner> actualOwner2 = ownerService.findByFirstName("Joe");
        final Owner owner2 = actualOwner2.getFirst();
        assertThat(owner2).isNotNull();
        assertThat(owner2.pets).extracting(Pet::getName).contains("Miro");
    }

    @Test
    @Transactional
    public void shouldFindByFirstNameAndLastName() {
        final Optional<Owner> actualOwner = ownerService.findByFirstNameAndLastName("Joe", "Doe");
        final Owner owner = actualOwner.orElse(null);
        assertThat(owner).isNotNull();
        assertThat(owner.lastName).isEqualTo("Doe");
        assertThat(owner.firstName).isEqualTo("Joe");

    }
}
