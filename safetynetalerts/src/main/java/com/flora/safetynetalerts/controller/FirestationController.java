package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("firestation")
public class FirestationController {
    @Autowired
    FirestationService firestationService;

    @GetMapping("")
    public ResponseEntity<List<Firestation>> getFirestations() {
        List<Firestation> firestations;
            firestations = firestationService.getFirestations();
        return ResponseEntity.ok(firestations);
    }

    @GetMapping("/{station}")
    public Firestation getFirestation(@PathVariable("station") Long station, @RequestParam(required = false) String address) {
        Firestation firestation;
        if (address != null)  {
            firestation = firestationService.getFirestationByAddress(address);
        } else {
            firestation = firestationService.getFirestation(station);
        }
        return firestation;
    }

    @PostMapping("")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation) {
        Firestation newFirestation = firestationService.createFirestation(firestation);
        return new ResponseEntity<>(newFirestation, HttpStatus.CREATED);
    }

    @PutMapping("/{station}")
    public ResponseEntity<Person> updateFirestation(@PathVariable("station") Long station, @RequestBody Person firestationToUpdate){
        Person updatedPerson = firestationService.updateFirestation(firestationToUpdate);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{station}")
    public void deleteFirestationByStation(@PathVariable("station") Long station) {
        firestationService.deleteFirestation(station);
    }

}
