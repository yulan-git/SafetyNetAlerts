package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.entities.PersonId;
import com.flora.safetynetalerts.entities.Role;
import com.flora.safetynetalerts.repository.RoleRepository;
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
    private PersonService personService;
    @Autowired
    private RoleRepository roleRepository;

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

    @GetMapping("/{lastname}/{phone}")
    public Person getPerson(@PathVariable("lastname") String lastname, @PathVariable("phone") String phone) {
        PersonId personId = new PersonId(lastname, phone);
        return personService.getPerson(personId);
    }

    @PostMapping("/user")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        Role role = roleRepository.getById(1);
        Person newPerson = personService.createPerson(personDto, role);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }
    @PostMapping("/admin")
    public ResponseEntity<Person> createAdmin(@RequestBody PersonDto person) {
        Role role = roleRepository.getById(2);
        Person newPerson = personService.createPerson(person, role);
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
