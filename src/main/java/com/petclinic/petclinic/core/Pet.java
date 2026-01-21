package com.petclinic.petclinic.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
