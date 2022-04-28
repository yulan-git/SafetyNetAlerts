package com.flora.safetynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    /*@EmbeddedId
    private PersonId personID;*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long personId;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    private String phone;

    @NotBlank
    @Size(min = 2, max = 20)
    private String birthday;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicationsList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergiesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;
/*
    @NotBlank
    @JsonIgnore
    private String password;*/

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

 public Person(PersonId personId, String firstName, String email, String birthday,List<String> medicationsList,  List<String> allergiesList, Address newAddress, Role roleUser) {
 }
}
