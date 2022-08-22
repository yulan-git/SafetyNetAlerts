package com.flora.safetynetalerts.dto;

import com.flora.safetynetalerts.entities.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PersonNewDto {
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public PersonNewDto(String email, String encode) {
    }
}
