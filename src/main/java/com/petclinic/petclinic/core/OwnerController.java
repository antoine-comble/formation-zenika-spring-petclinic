package com.petclinic.petclinic.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/{id}")
    public Optional<Owner> findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("/owner/search")
    public List<Owner> findByFirstName(@RequestParam String firstName) {
        return ownerService.findByFirstName(firstName);
    }

    @PostMapping(value = "/owner")
    public Owner save(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }
}
