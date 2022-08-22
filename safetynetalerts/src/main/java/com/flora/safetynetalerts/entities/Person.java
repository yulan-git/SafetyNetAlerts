package com.flora.safetynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @EmbeddedId
    private PersonId personId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @NotBlank
    @Size(max = 250)
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicationsList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergiesList = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

  /*  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    List<Alert> alertsList = new ArrayList<>();*/
}
