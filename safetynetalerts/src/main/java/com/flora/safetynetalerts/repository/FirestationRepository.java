package com.flora.safetynetalerts.repository;

import com.flora.safetynetalerts.entities.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirestationRepository extends JpaRepository<Firestation, Long> {
}
