package com.flora.safetynetalerts.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Firestation {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long station;

        @OneToMany(fetch= FetchType.LAZY)
        private List<Address> addressList = new ArrayList<>();

        @OneToMany(fetch= FetchType.LAZY)
        private List<Alert> alertList = new ArrayList<>();

}
