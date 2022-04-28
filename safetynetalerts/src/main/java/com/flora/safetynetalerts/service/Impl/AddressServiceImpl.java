package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.repository.AddressRepository;
import com.flora.safetynetalerts.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address) {
        List<Address> addressList = addressRepository.findAll();
        for (Address existingAddress: addressList) {
            if(existingAddress.toString() != address.toString()){
                addressRepository.save(address);
            }else{
                System.out.println("Cette adresse existe déjà !");
            }

        }
        System.out.println("Adresse créée ============================> " + address);
        return address;
    }

    @Override
    public Address updateAddress(Address addressToUpdate) {
        return null;
    }


}
