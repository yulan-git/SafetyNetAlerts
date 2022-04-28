package com.flora.safetynetalerts.entities;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PersonId implements Serializable {

    private String lastName;

    private String phone;

    public PersonId(String lastName, String phone) {
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonId{" +
                "lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

