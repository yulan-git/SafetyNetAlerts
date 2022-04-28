package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(Address address);
    Address updateAddress(Address addressToUpdate);
    List<Address> getAddresses();
}
