package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Firestation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Mock
    FirestationService firestationService;

    public void setFakeFirestation() {
        Firestation firestation = new Firestation();
        firestation.setStation(1L);
    }

    @BeforeEach
    public void set_up() {
        setFakeFirestation();
    }

    @Test
    public void deleteFirestation(){
        firestationService.deleteFirestation(1L);
        Mockito.verify(firestationService, Mockito.times(1)).deleteFirestation(1L);
    }
}
