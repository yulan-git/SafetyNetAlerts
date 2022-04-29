package com.flora.safetynetalerts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonId implements Serializable {

    private String lastName;

    private String phone;

    @Override
    public String toString() {
        return "PersonId{" +
                "lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

