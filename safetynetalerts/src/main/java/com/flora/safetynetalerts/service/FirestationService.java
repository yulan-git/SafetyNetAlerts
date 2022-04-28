package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FirestationService {
    List<Firestation> getFirestations();
    Firestation getFirestationByAddress(String address);
    Firestation getFirestation(Long station);
    Firestation createFirestation(Firestation firestation);
    Person updateFirestation(Person firestationToUpdate);
    void deleteFirestation(Long station);
}
