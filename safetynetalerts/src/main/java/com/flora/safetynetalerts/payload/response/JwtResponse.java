package com.flora.safetynetalerts.payload.response;

import com.flora.safetynetalerts.entities.PersonId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private PersonId id;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, PersonId id, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
