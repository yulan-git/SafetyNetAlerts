package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("")
    public ResponseEntity<List<Address>> getAdresses() {
        List<Address> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public Address getAddress(@PathVariable("addressId") Long addressId) {
        Address address = addressService.getAddress(addressId);
        return address;
    }

    @PostMapping("")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address newAddress = addressService.createAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable("addressId") Long addressId, @RequestBody Address addressToUpdate){
        Address updatedAddress = addressService.updateAddress(addressToUpdate);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

}
