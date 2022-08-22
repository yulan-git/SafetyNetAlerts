package com.flora.safetynetalerts.utils;

import com.flora.safetynetalerts.entities.*;
import com.flora.safetynetalerts.repository.*;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    AlertRepository alertRepository;

   /* @Value("classpath:data/data.json")
    Resource resourceFile;*/

    public void generatePersons() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(Paths.get(filePath));

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();

        Any roleAny = any.get("roles");
        Set<Role> roleUser = generateRoles(roleAny);

        Any addressAny = any.get("addresses");
        generateAddress(addressAny);

        Any firestationAny = any.get("firestations");
        generateFirestation(firestationAny);

        /*Any alertAny = any.get("alerts");
        generateAlert(alertAny);*/

        Any personAny = any.get("persons");
        personAny.forEach(a -> {
            Address newAddress = new Address();
            newAddress.setAddressId(a.get("addressId").toLong());

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

          /*  List<Any> alerts = a.get("alertsList").asList();
            List<Alert> alertsList = new ArrayList<>();
            for (Any alert : alerts){
                Alert newAlert = new Alert();
                newAlert.setUuid(UUID.fromString(alert.get("uuid").toString()));
                System.out.println("Alert --------> " + newAlert);
                alertsList.add(newAlert);
                System.out.println("AlertList --------> " + alertsList);
            }*/

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

    public Set<Role> generateRoles(Any roles) {
        Set<Role> rolesUser = new HashSet<>();
        for (Any a : roles) {
            String name = a.get("name").toString();
            RoleEnum rolesPerson = name.equals("USER") ? RoleEnum.USER : RoleEnum.ADMIN;
            Role role = new Role(
                    null,
                    rolesPerson
            );
            if (name.equals("USER")) {
                rolesUser.add(role);
            }
            this.roleRepository.save(role);
        };
        return rolesUser;
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
                    addresses
            );
            this.firestationRepository.save(fireStation);
        }
    }

/*    public void generateAlert(Any alerts) {
        for (Any a : alerts) {
            UUID uuid = UUID.fromString(a.get("uuid").toString());
            String date = a.get("date").toString();
            LocalDate localDate = LocalDate.parse(date);

            String description = a.get("description").toString();
            TypeEnum type = a.get("type").as(TypeEnum.class);
            StatusEnum status = a.get("status").as(StatusEnum.class);
            Long addressId = a.get("addressId").toLong();
            //Optional<Address> address = addressRepository.findById(addressId);
            Address address1 = new Address();
            address1.setAddressId(addressId);
            Firestation firestation = new Firestation();
            firestation.setStation(a.get("station").toLong());

            Alert alert = new Alert(
                    uuid,
                    localDate,
                    description,
                    address1,
                    type,
                    status,
                    firestation,
                    listPerson
            );
            this.alertRepository.save(alert);
        }
    }*/

    @Override
    public void run(String... args) throws Exception {
        generatePersons();
    }
}
