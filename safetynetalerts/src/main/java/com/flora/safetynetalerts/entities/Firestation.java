package com.flora.safetynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        @OneToMany(fetch= FetchType.LAZY)
        private List<Address> addressList = new ArrayList<>();

       /* @OneToMany(fetch= FetchType.LAZY)
        private List<Alert> alertList = new ArrayList<>();*/

}
