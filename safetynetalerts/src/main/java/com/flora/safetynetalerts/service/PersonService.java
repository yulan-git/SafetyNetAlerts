package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.entities.PersonId;
import com.flora.safetynetalerts.entities.Role;

import java.util.List;

public interface PersonService {
    List<Person> getPersonsByBirthday(String birthday);
    List<Person> getPersons();
    Person getPerson(PersonId personId);
    Person createPerson(PersonDto person, Role role);
    Person updatePerson(Person personToUpdate);
    void deletePerson(Long personId);
}
