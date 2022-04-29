package com.flora.safetynetalerts.dto;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.PersonId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class PersonDto {
    private String lastName;
    private String phone;
    private String firstName;
    private Date birthday;
    private String email;
    private String password;
    private Address address;
    private List<String> medicationsList;
    private List<String> allergiesList;

    public PersonId toPersonId(){
        return new PersonId(lastName, phone);
    }
}
