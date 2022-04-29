package com.flora.safetynetalerts.service.Impl;

import com.flora.safetynetalerts.entities.*;
import com.flora.safetynetalerts.repository.AlertRepository;
import com.flora.safetynetalerts.service.AlertService;
import com.flora.safetynetalerts.service.FirestationService;
import com.flora.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private PersonService personService;

    @Override
    public Alert updateAlert(Alert alertToUpdate) {
        return alertRepository.save(alertToUpdate);
    }

    @Override
    public Alert createAlert(Alert alert) {
        LocalDate todayDate = LocalDate.now();
        StatusEnum status = StatusEnum.PENDING;
        Firestation firestation = firestationService.getFirestationByAddress(alert.getAddress().getAddressId());
        List<Person> personList = personService.getPersonListByAddress(alert.getAddress().getAddressId());

        Alert newAlert = new Alert();
        newAlert.setDate(todayDate);
        newAlert.setDescription(alert.getDescription());
        newAlert.setAddress(alert.getAddress());
        newAlert.setFirestation(firestation);
        newAlert.setStatus(status);
        newAlert.setType(alert.getType());
        newAlert.setPersonList(personList);

        this.alertRepository.save(newAlert);
        return newAlert;
    }

    @Override
    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Alert getAlertByUuid(UUID uuid) {
        List<Alert> alertList = alertRepository.findAll();
        Alert currentAlert = new Alert();
        for (Alert alert: alertList) {
            int result = uuid.compareTo(alert.getUuid());
            if (result == 0){
                currentAlert = alert;
            }
        }
        return currentAlert;
    }
}
