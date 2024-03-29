package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.entities.PersonId;
import com.flora.safetynetalerts.entities.Role;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface PersonService {
    List<Person> getPersonsByBirthday(String birthday) throws ParseException;
    List<Person> getPersons();
    Person getPerson(PersonId personId);
    Person createPerson(PersonDto person, Set<Role> role);
    Person updatePerson(Person personToUpdate);
    void deletePerson(Long personId);
    List<Person> getPersonListByAddress(Long addressId);
}
