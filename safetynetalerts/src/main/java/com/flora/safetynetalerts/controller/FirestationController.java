package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.service.AddressService;
import com.flora.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("firestation")
public class FirestationController {
    @Autowired
    FirestationService firestationService;
    @Autowired
    AddressService addressService;

    private ResponseEntity responseEntity;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Firestation>> getFirestations() {
        List<Firestation> firestations;
            firestations = firestationService.getFirestations();
        return ResponseEntity.ok(firestations);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/address")
    public Firestation getFirestationByAddress(@RequestParam(required = false) Long addressId) {
        Firestation firestation = firestationService.getFirestationByAddress(addressId);
        return firestation;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{station}")
    public Firestation getFirestation(@PathVariable("station") Long station) {
        Firestation firestation = firestationService.getFirestation(station);
        return firestation;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation) throws Exception {
        Firestation newFirestation = firestationService.createFirestation(firestation);
        return new ResponseEntity<>(newFirestation, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/{station}")
    public ResponseEntity<Firestation> updateFirestation(@PathVariable("station") Long station, @RequestBody Firestation firestationToUpdate){
        Firestation updatedPerson = firestationService.updateFirestation(firestationToUpdate);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping("/{station}")
    public ResponseEntity<Firestation> deleteFirestationByStation(@PathVariable("station") Long station) {
        firestationService.deleteFirestation(station);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
