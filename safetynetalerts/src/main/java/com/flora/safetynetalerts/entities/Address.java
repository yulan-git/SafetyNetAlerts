package com.flora.safetynetalerts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 2, max = 50)
    private  String address;

    @NotBlank
    @Size(min = 2, max = 20)
    private String city;

    @NotBlank
    @Size(min = 2, max = 20)
    private String zip;

    @Override
    public String toString() {
        return address + city + zip;
    }
}
