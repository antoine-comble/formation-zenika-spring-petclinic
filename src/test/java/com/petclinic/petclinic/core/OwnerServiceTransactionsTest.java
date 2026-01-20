package com.petclinic.petclinic.core;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class OwnerServiceTransactionsTest {

    @Autowired
    OwnerService ownerService;

    @Test
    @Transactional
    public void shouldRunInTransaction() {
        assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
    }

    @Test
    @Transactional
    public void shouldTransferFunds() {
        final Owner ownerToCredit = ownerService.save(new Owner(null, "Jimi", "Hendrix", 0));
        final Owner ownerToDebit = ownerService.save(new Owner(null, "Robert", "Plant", 1000));
        ownerService.transferFounds(ownerToCredit, ownerToDebit, 200);

        final Optional<Owner> jimiHendrix = ownerService.findByFirstNameAndLastName("Jimi", "Hendrix");
        assertThat(jimiHendrix).isNotEmpty();
        assertThat(jimiHendrix.orElse(null).accountStatement).isEqualTo(200);

        final Optional<Owner> robertPlant = ownerService.findByFirstNameAndLastName("Robert", "Plant");
        assertThat(robertPlant).isNotEmpty();
        assertThat(robertPlant.orElse(null).accountStatement).isEqualTo(800);
    }

    @Test
    @Transactional
    public void shouldTransferFunds2() {
        final Owner ownerToCredit = ownerService.save(new Owner(null, "Jimi", "Hendrix", 200));
        final Owner ownerToDebit = ownerService.save(new Owner(null, "Robert", "Plant", 1000));
        ownerService.transferFounds(ownerToCredit, ownerToDebit, 200);

        final Optional<Owner> jimiHendrix = ownerService.findByFirstNameAndLastName("Jimi", "Hendrix");
        assertThat(jimiHendrix).isNotEmpty();
        assertThat(jimiHendrix.orElse(null).accountStatement).isEqualTo(400);

        final Optional<Owner> robertPlant = ownerService.findByFirstNameAndLastName("Robert", "Plant");
        assertThat(robertPlant).isNotEmpty();
        assertThat(robertPlant.orElse(null).accountStatement).isEqualTo(800);
    }

    @Test
    @Transactional
    public void shouldTransferFundsNegativeAccountThrowException() {
        final Owner ownerToCredit = ownerService.save(new Owner(null, "Jimi", "Hendrix", 200));
        final Owner ownerToDebit = ownerService.save(new Owner(null, "Robert", "Plant", 100));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ownerService.transferFounds(ownerToCredit, ownerToDebit, 200))
                .withMessageContaining("Account value cannot be below 0");
    }
}
