package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.service.FirestationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationServiceImpl implements FirestationService {
    @Override
    public List<Firestation> getFirestations() {
        return null;
    }

    @Override
    public Firestation getFirestationByAddress(String address) {
        return null;
    }

    @Override
    public Firestation getFirestation(Long station) {
        return null;
    }

    @Override
    public Firestation createFirestation(Firestation firestation) {
        return null;
    }

    @Override
    public Person updateFirestation(Person firestationToUpdate) {
        return null;
    }

    @Override
    public void deleteFirestation(Long station) {

    }
}
