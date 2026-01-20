package com.petclinic.petclinic.core;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue
    Long id;

    String firstName;

    String lastName;

    double accountStatement;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id")
    List<Pet> pets;

    public Owner() {
    }

    public Owner(Long id, String firstName, String lastName, double accountStatement) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountStatement = accountStatement;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
