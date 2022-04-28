package com.flora.safetynetalerts.repository;

import com.flora.safetynetalerts.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
