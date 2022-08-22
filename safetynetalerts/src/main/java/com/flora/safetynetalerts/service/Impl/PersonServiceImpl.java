package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.*;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.PersonRepository;
import com.flora.safetynetalerts.service.AddressService;
import com.flora.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Person> getPersonsByBirthday(String birthday) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Person> persons = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        for (Person person: persons) {
            Date currentBirthday = sdf.parse(birthday);
            int result = currentBirthday.compareTo(person.getBirthday());
            if(result==0){
                personList.add(person);
            }
        }
        return personList;
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
    public Person createPerson(PersonDto personDto, Set<Role> role) {
        Person newPerson = new Person();
        String newPassword = personDto.getPassword();
        Address address = addressService.getAddressById(personDto.getAddress());
        newPerson.setPersonId(personDto.toPersonId());
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setEmail(personDto.getEmail());
        newPerson.setPassword(personDto.getPassword());
        newPerson.setBirthday(personDto.getBirthday());
        newPerson.setMedicationsList(personDto.getMedicationsList());
        newPerson.setAllergiesList(personDto.getAllergiesList());
        newPerson.setAddress(address);
        newPerson.setRoles(role);
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

    @Override
    public List<Person> getPersonListByAddress(Long addressId) {
        List<Person> persons = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        Address currentAddress = addressRepository.findById(addressId).get();
        for (Person person  : persons) {
            Address address = person.getAddress();
            if (address == currentAddress) {
               personList.add(person);
                }
            }
        return personList;
    }

}
