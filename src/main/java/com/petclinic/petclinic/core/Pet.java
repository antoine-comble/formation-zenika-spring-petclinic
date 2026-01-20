package com.petclinic.petclinic.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    Long id;

    String type;
    String name;

    public Pet(Long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
