package com.petclinic.petclinic.core;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "referenceNumber", unique = true)
    String referenceNumber;
    @Column(name = "date")
    LocalDate date;
    @Column(name = "purpose")
    String purpose;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    Pet pet;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id")
    Owner owner;

    public Visit(Long id, String referenceNumber, LocalDate date, String purpose) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.date = date;
        this.purpose = purpose;
    }

    public Visit() {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Visit)) return false;
        return id.equals(((Visit) obj).id) && referenceNumber.equals(((Visit) obj).referenceNumber) && date.equals(((Visit) obj).date) && purpose.equals(((Visit) obj).purpose);
    }

    public Pet getPet() {
        return this.pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return this.owner;
    }
}
