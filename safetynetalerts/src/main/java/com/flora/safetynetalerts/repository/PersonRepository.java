package com.flora.safetynetalerts.repository;

import com.flora.safetynetalerts.entities.Person;
import com.flora.safetynetalerts.entities.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId> {

}
