package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {


    @Override
    public List<Person> getPersonsByBirthday(String birthday) {
        return null;
    }

    @Override
    public List<Person> getPersons() {
        return null;
    }

    @Override
    public Person getPerson(Long personId) {
        return null;
    }

    @Override
    public Person createPerson(Person person) {
        return null;
    }

    @Override
    public Person updatePerson(Person personToUpdate) {
        return null;
    }

    @Override
    public void deletePerson(Long personId) {

    }
}
