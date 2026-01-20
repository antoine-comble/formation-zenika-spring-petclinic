package com.petclinic.petclinic.core;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> findByFirstName(String firstname) {
        return ownerRepository.findByFirstName(firstname);
    }
}
