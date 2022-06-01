package com.flora.safetynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alerts")
public class Alert {
    @Id
    private UUID uuid = UUID.randomUUID();

    //@NotNull
    private LocalDate date;

    //@NotNull
    //@Size(min = 2, max = 250)
    private String description;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusEnum status;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Person> personList = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="station",insertable=false,updatable=false)
    private Firestation firestation;

}
