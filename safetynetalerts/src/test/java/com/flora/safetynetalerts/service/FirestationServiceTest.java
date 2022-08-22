package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.exceptions.exception.BusinessException;
import com.flora.safetynetalerts.exceptions.exception.TechnicalException;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.FirestationRepository;
import com.flora.safetynetalerts.service.Impl.FirestationServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class FirestationServiceTest {

    Firestation fakeFirestation = new Firestation();

    public Firestation setFakeFirestation() {

        List<Address> addressList = new ArrayList<Address>();
        Address address = new Address(1L, "80 rue des rondeaux", "Paris", "75020" );
        addressList.add(address);
        fakeFirestation.setStation(1L);
        fakeFirestation.setAddressList(addressList);
        return fakeFirestation;
    }

    public FirestationService getFirestationService(FirestationRepository firestationRepository, AddressRepository addressRepository) {
        return new FirestationServiceImpl(firestationRepository, addressRepository);
    }

    @BeforeEach
    public void set_up(){
        setFakeFirestation();
    };

    @Test
    public void createFirestation() throws Exception {
        AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
        FirestationRepository firestationRepository = Mockito.mock(FirestationRepository.class);

        Address address = Address.builder().addressId(1L).address("80 rue des rondeaux").city("Paris").zip("75020").build();

        Mockito.when(addressRepository.getById(1L)).thenReturn(address);
        Mockito.when(firestationRepository.save(any())).thenReturn(Firestation.builder().station(1L).addressList(List.of(address)).build());

        Firestation newFirestation = getFirestationService(firestationRepository,addressRepository).createFirestation(fakeFirestation);
        Assertions.assertEquals(fakeFirestation, newFirestation);
        Mockito.verify(addressRepository, Mockito.times(1)).getById(1L);
        Mockito.verify(firestationRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void createFirestation_technical_error(){
        AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
        FirestationRepository firestationRepository = Mockito.mock(FirestationRepository.class);

        Address address = Address.builder().addressId(1L).address("80 rue des rondeaux").city("Paris").zip("75020").build();

        Mockito.when(addressRepository.getById(1L)).thenReturn(address);
        Mockito.when(firestationRepository.save(any())).thenThrow(new DataIntegrityViolationException("toto"));

        Exception e = Assertions.assertThrows(Exception.class, () -> getFirestationService(firestationRepository,addressRepository).createFirestation(fakeFirestation));
        Assertions.assertEquals(TechnicalException.class, e.getClass());
        Assertions.assertTrue(e.getMessage().contains("toto"));
    }

    @Test
    public void createFirestation_business_error(){
        AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
        FirestationRepository firestationRepository = Mockito.mock(FirestationRepository.class);

        Exception e = Assertions.assertThrows(Exception.class, () -> getFirestationService(firestationRepository,
                addressRepository).createFirestation(null));
        Assertions.assertEquals(BusinessException.class, e.getClass());
        Assertions.assertTrue(e.getMessage().contains("null"));
    }
/*
    @Test
    public void deleteFirestation(){
        firestationService.deleteFirestation(1L);
        Mockito.verify(firestationService, Mockito.times(1)).deleteFirestation(1L);
    }*/
}
