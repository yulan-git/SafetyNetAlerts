package com.flora.safetynetalerts.utils;

import com.flora.safetynetalerts.entities.*;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.FirestationRepository;
import com.flora.safetynetalerts.repository.PersonRepository;
import com.flora.safetynetalerts.repository.RoleRepository;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Generator implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FirestationRepository firestationRepository;

    public void generatePersons() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();

        Any roleAny = any.get("roles");
        Role roleUser = generateRoles(roleAny);

        Any addressAny = any.get("addresses");
        generateAddress(addressAny);

        Any firestationAny = any.get("firestations");
        generateFirestation(firestationAny);

        Any personAny = any.get("persons");
        personAny.forEach(a -> {
            Address newAddress = addressRepository.getById(a.get("addressId").toLong());

            List<Any> medications = a.get("medicationsList").asList();
            List<String> medocList = new ArrayList<>();
            for (Any medoc : medications){
                medocList.add(medoc.toString());
            }

            List<Any> allergies = a.get("allergiesList").asList();
            List<String> allergyList = new ArrayList<>();
            for (Any allergy : allergies){
                allergyList.add(allergy.toString());
            }

            PersonId personId = new PersonId(a.get("lastName").toString(), a.get("phone").toString());
            Date date = new Date();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(a.get("birthday").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Person person = new Person(
                    personId,
                    //a.get("lastName").toString(),
                    a.get("firstName").toString(),
                    a.get("email").toString(),
                    //a.get("phone").toString(),
                    date,
                    a.get("password").toString(),
                    medocList,
                    allergyList,
                    newAddress,
                    roleUser
            );
            this.personRepository.save(person);
        });
    }

    private void generateAddress(Any addresse) {
        for (Any a : addresse) {
            String address = a.get("address").toString();
            String city = a.get("city").toString();
            String zip = a.get("zip").toString();
            Address newAddress = new Address(
                    null,
                    address,
                    city,
                    zip
            );
            this.addressRepository.save(newAddress);
        }
    }

    public Role generateRoles(Any roles) {
        Role roleUser = null;
        for (Any a : roles) {
            String label = a.get("label").toString();
            RoleEnum rolesPerson = label.equals("USER") ? RoleEnum.USER : RoleEnum.ADMIN;
            Role role = new Role(
                    null,
                    rolesPerson
            );
            if (label.equals("USER")) {
                roleUser = role;
            }
            this.roleRepository.save(role);
        };
        return roleUser;
    };

    public void generateFirestation(Any firestations) {
        for (Any a : firestations) {
            Long station = a.get("station").toLong();

            List<Any> addressList = a.get("addressList").asList();
            List<Address> addresses = new ArrayList<>();
            for (Any address : addressList) {
                Address newAddress = new Address();
                newAddress.setAddressId(address.get("addressId").toLong());
                newAddress.setAddress(address.get("address").toString());
                newAddress.setCity(address.get("city").toString());
                newAddress.setZip(address.get("zip").toString());
                addresses.add(newAddress);
            }

            Firestation fireStation = new Firestation(
                    station,
                    addresses,
                    null
            );
            this.firestationRepository.save(fireStation);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        generatePersons();
    }
}
