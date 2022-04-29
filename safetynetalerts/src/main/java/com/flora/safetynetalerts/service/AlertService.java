package com.flora.safetynetalerts.service;

import com.flora.safetynetalerts.entities.Alert;

import java.util.List;
import java.util.UUID;

public interface AlertService {
    Alert updateAlert(Alert alertToUpdate);
    Alert createAlert(Alert alert);
    List<Alert> getAlerts();

    Alert getAlertByUuid(UUID uuid);
}
