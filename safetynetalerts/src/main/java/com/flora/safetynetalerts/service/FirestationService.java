package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FirestationService {
    List<Firestation> getFirestations();
    Firestation getFirestationByAddress(Long addressId);
    Firestation getFirestation(Long station);
    Firestation createFirestation(Firestation firestation);
    Firestation updateFirestation(Firestation firestationToUpdate);
    void deleteFirestation(Long station);
}
