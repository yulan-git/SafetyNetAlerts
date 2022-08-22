package com.flora.safetynetalerts.validation;

import com.flora.safetynetalerts.entities.PersonId;
import com.flora.safetynetalerts.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthentificationValidation {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public UserDetailsImpl getUserDetails(){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;
    }

    public PersonId getTokenUserId(){
        UserDetailsImpl userDetails = getUserDetails();
        PersonId tokenUserId = userDetails.getId();
        return tokenUserId;
    }

    public boolean isAdmin(){
        UserDetailsImpl userDetails = getUserDetails();
        String roles = userDetails.getAuthorities().toString();
        if (roles.contains("ADMIN")){
            return true;
        } else {
            return false;
        }
    }




}
