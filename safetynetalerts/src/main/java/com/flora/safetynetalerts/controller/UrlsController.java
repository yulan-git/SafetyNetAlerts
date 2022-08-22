package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class UrlsController {
    @Autowired
    UrlsService urlService;

    //http://localhost:8080/childAlert?address=<address>
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/childAlert")
    public ResponseEntity<Map<String, Object>> getChildrenByAddress(@RequestParam Long address){
        Map<String, Object> childrenList = urlService.getChildrenList(address);
        return ResponseEntity.ok(childrenList);
    }

    //http://localhost:8080/communityEmail?city=<city>
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getEmailList(@RequestParam String city){
        List<String> emailList = urlService.getEmailList(city);
        return ResponseEntity.ok(emailList);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/station")
    public Map<String, Object> getFireStationByStation(@RequestParam("stationNumber") Long stationNumber){
        Map<String, Object> countPerson = urlService.getFireStationByStationNumber(stationNumber);
        return countPerson;
    }

    /*http://localhost:8080/phoneAlert?firestation=<firestation_number>*/
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhones(@RequestParam Long firestation){
        List<String> phonesList = urlService.getPhones(firestation);
        return ResponseEntity.ok(phonesList);
    }


    /*http://localhost:8080/fire?address=<address>*/
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/fire")
    public ResponseEntity<Map<String, Object>> getPersonsWithAddress(@RequestParam Long address){
        Map<String, Object> personsInfos = urlService.getPersonsWithAddress(address);
        return ResponseEntity.ok(personsInfos);
    }


    /*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.*/
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/flood/stations")
    public ResponseEntity<Map<String, Object>> getPersonsByFirestation(@RequestParam List<Long> stations){
        Map<String, Object> personsInfos = urlService.getPersonsByFirestation(stations);
        return ResponseEntity.ok(personsInfos);
    }

    /*http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    toutes apparaître.*/
}
