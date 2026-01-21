package com.petclinic.petclinic.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @Override
    public String toString() {
        return this.id + "-" + this.firstName + "-" + this.lastName + "-" + this.accountStatement;
    }
}
