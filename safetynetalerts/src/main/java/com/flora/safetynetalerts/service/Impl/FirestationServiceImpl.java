package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.exceptions.exception.BusinessException;
import com.flora.safetynetalerts.exceptions.exception.TechnicalException;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.repository.FirestationRepository;
import com.flora.safetynetalerts.service.FirestationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirestationServiceImpl implements FirestationService {

    @NonNull
    FirestationRepository firestationRepository;
    @NonNull
    AddressRepository addressRepository;

    @Override
    public List<Firestation> getFirestations() {
        return firestationRepository.findAll();
    }

    @Override
    public Firestation getFirestationByAddress(Long addressId) {
        List<Firestation> firestations = firestationRepository.findAll();
        Address currentAddress = addressRepository.findById(addressId).get();
        Firestation firestationToReturn = null;
            for (Firestation firestation : firestations) {
                List<Address> addresses = firestation.getAddressList();
                for (Address address : addresses) {
                    if (address == currentAddress) {
                        firestationToReturn = firestation;
                    }
                }
            }
        return firestationToReturn;
    }

    @Override
    public Firestation getFirestation(Long station) {
        Optional<Firestation> fireStation = firestationRepository.findById(station);
        return fireStation.get();
    }

    @Override
    public Firestation createFirestation(Firestation firestation) throws TechnicalException, BusinessException {
        try{
            if (firestation == null){
                throw new BusinessException("firestation is null");
            }
            if (firestation.getAddressList() == null || firestation.getAddressList().size() == 0){
                throw new BusinessException("Minimum of one address required");
            }
            List<Address> addressList = new ArrayList<>();
            Firestation newFireStation= new Firestation();
        //Optional<List<Address>> currentAddressList = addressRepository.findAll();
            for (Address address : firestation.getAddressList()){
            Address existingAddress = addressRepository.getById(address.getAddressId());
            addressList.add(existingAddress);
            }
            newFireStation.setStation(firestation.getStation());
            newFireStation.setAddressList(addressList);
            firestationRepository.save(newFireStation);
            return newFireStation;
        } catch (BusinessException e){
            FirestationServiceImpl.log.warn(e.getMessage());
            throw e;
        }
        catch (Exception e){
            FirestationServiceImpl.log.error(e.getMessage());
            throw new TechnicalException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Firestation updateFirestation(Firestation firestationToUpdate) {
        return firestationRepository.save(firestationToUpdate);
    }

    @Override
    public void deleteFirestation(Long station) {
        Optional<Firestation> fireStationOptional = firestationRepository.findById(station);
        Firestation fireStation = null;
        if (fireStationOptional.isPresent()) {
            fireStation = fireStationOptional.get();
            firestationRepository.delete(fireStation);
        }
    }
}
