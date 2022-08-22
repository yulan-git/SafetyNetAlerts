package com.flora.safetynetalerts.controller;


import com.flora.safetynetalerts.dto.PersonDto;
import com.flora.safetynetalerts.entities.Role;
import com.flora.safetynetalerts.entities.RoleEnum;
import com.flora.safetynetalerts.payload.request.LoginRequest;
import com.flora.safetynetalerts.payload.request.SignupRequest;
import com.flora.safetynetalerts.payload.response.JwtResponse;
import com.flora.safetynetalerts.payload.response.MessageResponse;
import com.flora.safetynetalerts.repository.PersonRepository;
import com.flora.safetynetalerts.repository.RoleRepository;
import com.flora.safetynetalerts.security.jwt.JwtUtils;
import com.flora.safetynetalerts.security.services.UserDetailsImpl;
import com.flora.safetynetalerts.service.PersonService;
import com.flora.safetynetalerts.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonRepository userRepository;

    @Autowired
    PersonService personService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Loginrequest" + loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        SignupRequest sr = signupRequest;
        if(!PasswordValidator.isValid(signupRequest.getPassword())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password is not valid !"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        // Create new user's account
        PersonDto user = new PersonDto();
        user.setLastName(sr.getLastName());
        user.setPassword(encoder.encode(sr.getPassword()));
        user.setFirstName(sr.getFirstName());
        user.setPhone(sr.getPhone());
        user.setEmail(sr.getEmail());
        user.setAddress(sr.getAddress());

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        personService.createPerson(user, roles);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

