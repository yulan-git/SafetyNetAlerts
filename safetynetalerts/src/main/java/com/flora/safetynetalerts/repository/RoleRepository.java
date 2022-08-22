package com.flora.safetynetalerts.repository;

import com.flora.safetynetalerts.entities.Role;
import com.flora.safetynetalerts.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
