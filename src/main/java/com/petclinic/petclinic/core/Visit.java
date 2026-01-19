package com.petclinic.petclinic.core;

import java.time.LocalDate;

public class Visit {
    Long id;
    String referenceNumber;
    LocalDate date;
    String purpose;

    public Visit(Long id, String referenceNumber, LocalDate date, String purpose) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.date = date;
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Visit)) return false;
        return id.equals(((Visit) obj).id)
                && referenceNumber.equals(((Visit) obj).referenceNumber)
                && date.equals(((Visit) obj).date)
                && purpose.equals(((Visit) obj).purpose);
    }
}
