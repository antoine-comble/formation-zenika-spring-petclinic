package com.petclinic.petclinic.core;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(final OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public Owner save(final Owner owner) {
        return this.ownerRepository.save(owner);
    }

    public List<Owner> findByFirstName(final String firstname) {
        return this.ownerRepository.findByFirstName(firstname);
    }

    public Optional<Owner> findByFirstNameAndLastName(final String firstName, final String lastName) {
        return this.ownerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    public void transferFounds(final Owner ownerToCredit, final Owner ownerToDebit, final double amount) {
        creditAmount(ownerToCredit, amount);
        debitAmount(ownerToDebit, amount);
    }

    private void creditAmount(final Owner ownerToCredit, final double amount) {
        ownerToCredit.accountStatement = ownerToCredit.accountStatement + amount;
        this.ownerRepository.save(ownerToCredit);
    }

    private void debitAmount(final Owner ownerToDebit, final double amount) {
        final double ownerToDebitNewAmount = ownerToDebit.accountStatement - amount;
        if (ownerToDebitNewAmount < 0) {
            throw new RuntimeException("Account value cannot be below 0");
        }
        ownerToDebit.accountStatement = ownerToDebitNewAmount;
        this.ownerRepository.save(ownerToDebit);
    }

    public Optional<Owner> findById(final Long id) {
        return this.ownerRepository.findById(id);
    }
}
