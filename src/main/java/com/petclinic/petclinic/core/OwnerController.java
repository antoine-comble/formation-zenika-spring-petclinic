package com.petclinic.petclinic.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/owner")
public class OwnerController {

    @Autowired
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public Optional<Owner> findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("/search")
    public List<Owner> findByFirstName(@RequestParam String firstName) {
        return ownerService.findByFirstName(firstName);
    }
}
