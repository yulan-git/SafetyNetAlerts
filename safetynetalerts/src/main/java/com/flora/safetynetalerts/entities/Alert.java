package com.flora.safetynetalerts.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    private UUID uuid = UUID.randomUUID();

    @NotNull
    private Date date;

    @NotBlank
    @Size(min = 2, max = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusEnum status;

    @OneToMany(fetch = FetchType.LAZY)
    List<Person> personList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Firestation firestation;

}
