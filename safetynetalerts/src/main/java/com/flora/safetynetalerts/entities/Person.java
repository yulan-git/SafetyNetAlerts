package com.flora.safetynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String email;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

/*    @NotBlank
    @Size(min = 2, max = 20)
    private String password;*/

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicationsList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergiesList = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

}
