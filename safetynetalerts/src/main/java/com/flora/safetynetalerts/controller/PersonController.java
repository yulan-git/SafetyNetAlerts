package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(required = false) String birthday) {
        List<Person> persons;
        if (birthday != null)  {
            persons = personService.getPersonsByBirthday(birthday);
        } else {
            persons = personService.getPersons();
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable("personId") Long personId) {
        return personService.getPerson(personId);
    }

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
            Person newPerson = personService.createPerson(person);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") Long personId, @RequestBody Person personToUpdate){
            Person updatedPerson = personService.updatePerson(personToUpdate);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable("personId") Long personId) {
            personService.deletePerson(personId);
    }
}
