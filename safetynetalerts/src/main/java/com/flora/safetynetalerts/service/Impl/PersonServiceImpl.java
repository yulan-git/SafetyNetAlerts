package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.entities.PersonId;
import com.flora.safetynetalerts.entities.Role;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.PersonRepository;
import com.flora.safetynetalerts.service.AddressService;
import com.flora.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getPersonsByBirthday(String birthday) {
        return null;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(PersonId personId) {
        Optional<Person> person = personRepository.findById(personId);
        return person.get();
    }

    @Override
    public Person createPerson(PersonDto personDto, Role role) {
        Person newPerson = new Person();
        Address address = addressService.getAddressById(personDto.getAddress());
        newPerson.setPersonID(personDto.toPersonId());
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setEmail(personDto.getEmail());
        newPerson.setPassword(personDto.getPassword());
        newPerson.setBirthday(personDto.getBirthday());
        newPerson.setMedicationsList(personDto.getMedicationsList());
        newPerson.setAllergiesList(personDto.getAllergiesList());
        newPerson.setAddress(address);
        newPerson.setRole(role);
        this.personRepository.save(newPerson);
        return newPerson;
    }

    @Override
    public Person updatePerson(Person personToUpdate) {
        return null;
    }

    @Override
    public void deletePerson(Long personId) {

    }
}
