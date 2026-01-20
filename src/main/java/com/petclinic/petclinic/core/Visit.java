package com.petclinic.petclinic.core;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

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
}
