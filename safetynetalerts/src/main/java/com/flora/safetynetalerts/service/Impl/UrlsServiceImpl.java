package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.FirestationRepository;
import com.flora.safetynetalerts.repository.PersonRepository;
import com.flora.safetynetalerts.service.PersonService;
import com.flora.safetynetalerts.service.UrlsService;
import com.flora.safetynetalerts.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UrlsServiceImpl implements UrlsService {

    @Autowired
    DataUtils dataUtils;
    @Autowired
    private FirestationRepository fireStationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Map<String, Object> getChildrenList(Long addressId) {
        Map<String, Object> childrenList = new HashMap<>();
        Address address = addressRepository.getById(addressId);
        List<Person> personByAddress = new ArrayList<>();
        List<StringBuilder> infosChild = new ArrayList<>();
        for(Person person : personService.getPersons()){
            if(person.getAddress().equals(address)){
                personByAddress.add(person);
            } else {
                System.out.println("Pas de mineurs");
            }
        }

        List<Person> children = new ArrayList<>();
        for (Person person : personByAddress){
            if (dataUtils.getAge(person) <= 18){
                children.add(person);
            }
        }


        for (Person person : children){
            if (dataUtils.getAge(person) <= 18) {
                StringBuilder child = new StringBuilder();
                child.append(" " + person.getFirstName() + " " + person.getPersonId().getLastName() + " " +
                        dataUtils.getAge(person) + " ans");
                infosChild.add(child);
            }
        }

        if (infosChild.size() >0){
            childrenList.put("enfant: ", infosChild);
        } else{
            childrenList.put("enfant: ", "Pas d'enfant à cette adresse");
        }

        return childrenList;
    }

    @Override
    public Map<String, Object> getFireStationByStationNumber(Long station){
        Firestation fireStation = fireStationRepository.getById(station);
        List<Address> addressesInFireStation = fireStation.getAddressList();
        Map<String, Object> countPerson = new HashMap<>();
        List<String> infoPerson = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        Integer countAdults = 0;
        Integer countChildrens = 0;

        for (Address address : addressesInFireStation){
            List<Person> personsInAddress = personService.getPersonListByAddress(address.getAddressId());
            personList.addAll(personsInAddress);
        }
        for (Person person : personList){
            Integer age = dataUtils.getAge(person);
            if (age > 18){
                countAdults++;
            } else {
                countChildrens++;
            }
            StringBuilder infos = new StringBuilder();
            infos.append(person.getFirstName());
            infos.append(person.getPersonId());
            infos.append(person.getAddress());
            infoPerson.add(infos.toString());
        }
        countPerson.put("Personnes", infoPerson);
        countPerson.put("Mineurs", countChildrens);
        countPerson.put("Majeurs", countAdults);

        return countPerson;
    }

    @Override
    public List<String> getEmailList(String city) {
        List<String> emailList = new ArrayList<>();

        for (Person person : personRepository.findAll()) {
            if (person.getAddress().getCity().equals(city))
                emailList.add(person.getEmail());
        }
        return emailList;
    }

    @Override
    public List<String> getPhones(Long station) {
        List<String> phones = new ArrayList<>();
        for (Firestation firestation : fireStationRepository.findAll()) {
            if (firestation.getStation().equals(station)) {
                for (Address address: firestation.getAddressList()) {
                    for (Person person : personRepository.findAll()) {
                       if(address.equals(person.getAddress())) {
                            phones.add(person.getPersonId().getPhone());
                        }
                    }
                }
            }
        }
        return phones;
    }

    @Override
    public Map<String, Object> getPersonsWithAddress(Long addressId) {
        Map<String, Object> infos = new HashMap<>();
        List<Object> persons = new ArrayList<>();

        for (Firestation firestation : fireStationRepository.findAll()) {
            for (Address a : firestation.getAddressList()) {
                if (a.getAddressId().equals(addressId)) {
                    infos.put("Caserne désservant l'adresse: ", firestation.getStation());
                }
            }
        }

        for (Person person : personRepository.findAll()) {
            if (person.getAddress().getAddressId().equals(addressId)) {
                StringBuilder personInfo = new StringBuilder();
                personInfo.append(person.getFirstName());
                personInfo.append(" ");
                personInfo.append(person.getPersonId().getLastName());
                personInfo.append(", phone: ");
                personInfo.append(person.getPersonId().getPhone());
                personInfo.append(", ");
                personInfo.append(dataUtils.getAge(person));
                personInfo.append("ans, allergies: ");
                personInfo.append(person.getAllergiesList());
                personInfo.append(", médication: ");
                personInfo.append(person.getMedicationsList());

                persons.add(personInfo);

            }
            infos.put("Personnes à l'adresse: ", persons);

        }
        return infos;
    }

    @Override
    public Map<String, Object> getPersonsByFirestation(List<Long> stations) {
        Map<String, Object> caserne = new HashMap<>();
        List<Object> caserneList = new ArrayList<>();
        Map<String, Object> caserneAndInfos = new HashMap<>();

        //Get la firestation
        List<Firestation> firestationList = new ArrayList<>();
        for (Long station : stations) {
            System.out.println("SRATION ---------> " + station);
            Firestation firestation = fireStationRepository.getById(station);
            firestationList.add(firestation);

            //Boucle sur la liste d'addresses de cette firestation et on récupère la liste de personnes à cette adresse grace à la méthode précédente
            for (Firestation f:firestationList){
                System.out.println("firestation --->" + f);
                Map<String, Object> addressesList = new HashMap<>();
                List<Object> addressAndPersons = new ArrayList<>();

                for (Address a :f.getAddressList()){
                    Map<String, Object> personsInAddress = new HashMap<>();
                    Map<String, Object> people = this.getPersonsWithAddress(a.getAddressId());

                    personsInAddress.put("adresse", a);
                    personsInAddress.put("Liste de personnes", people);
                    addressAndPersons.add(personsInAddress);
                }
                addressesList.put("Liste adresses et personnes ", addressAndPersons);

                caserneAndInfos.put("Caserne : ", f.getStation());
                caserneAndInfos.put("Infos", addressesList);
            }
                caserneList.add(caserneAndInfos);
        }
            System.out.println("caserneList --->" + caserneList);
            caserne.put("Liste de casernes", caserneList);

        return caserne;
    }

}