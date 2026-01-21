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

    @Override
    public String toString() {
        return this.id + "-" + this.firstName + "-" + this.lastName + "-" + this.accountStatement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAccountStatement() {
        return accountStatement;
    }

    public void setAccountStatement(double accountStatement) {
        this.accountStatement = accountStatement;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
