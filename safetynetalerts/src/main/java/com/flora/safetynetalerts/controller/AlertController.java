package com.flora.safetynetalerts.controller;

import com.flora.safetynetalerts.entities.Address;
import com.flora.safetynetalerts.entities.Alert;
import com.flora.safetynetalerts.entities.Firestation;
import com.flora.safetynetalerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("alert")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @GetMapping("")
    public ResponseEntity<List<Alert>> getAlerts() {
        List<Alert> alertList = alertService.getAlerts();
        return new ResponseEntity<>(alertList, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public Alert getAlert(@PathVariable("uuid") UUID uuid) {
        Alert alert = alertService.getAlertByUuid(uuid);
        return alert;
    }

    @PostMapping("")
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert newAlert = alertService.createAlert(alert);
        return new ResponseEntity<>(newAlert, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Alert> updateAddress(@PathVariable("uuid") UUID uuid, @RequestBody Alert alertToUpdate){
        Alert updatedalert = alertService.updateAlert(alertToUpdate);
        return new ResponseEntity<>(updatedalert, HttpStatus.OK);
    }
}
