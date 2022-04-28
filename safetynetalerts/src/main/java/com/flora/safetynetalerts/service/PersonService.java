package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Person;
import java.util.List;

public interface PersonService {
    List<Person> getPersonsByBirthday(String birthday);
    List<Person> getPersons();
    Person getPerson(Long personId);
    Person createPerson(Person person);
    Person updatePerson(Person personToUpdate);
    void deletePerson(Long personId);
}
