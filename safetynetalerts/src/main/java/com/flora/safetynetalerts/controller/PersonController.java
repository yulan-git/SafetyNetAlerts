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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(required = false) String birthday) throws ParseException {
        List<Person> persons;
        if (birthday != null)  {
            persons = personService.getPersonsByBirthday(birthday);
        } else {
            persons = personService.getPersons();
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{lastname}/{phone}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Person getPerson(@PathVariable("lastname") String lastname, @PathVariable("phone") String phone) {
        PersonId personId = new PersonId(lastname, phone);
        return personService.getPerson(personId);
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        Role role = roleRepository.getById(1);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Person newPerson = personService.createPerson(personDto, roles);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }
    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Person> createAdmin(@RequestBody PersonDto person) {
        Role role = roleRepository.getById(2);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Person newPerson = personService.createPerson(person, roles);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") Long personId, @RequestBody Person personToUpdate){
            Person updatedPerson = personService.updatePerson(personToUpdate);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void deletePersonById(@PathVariable("personId") Long personId) {
            personService.deletePerson(personId);
    }
}
