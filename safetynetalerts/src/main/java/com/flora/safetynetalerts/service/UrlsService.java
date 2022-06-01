package com.flora.safetynetalerts.service;

import java.util.List;
import java.util.Map;

public interface UrlsService {
    Map<String, Object> getChildrenList(Long addressId);
    Map<String, Object> getFireStationByStationNumber(Long station);
    List<String> getEmailList(String city);
    List<String> getPhones(Long station);
    Map<String, Object> getPersonsWithAddress(Long addressId);
    Map<String, Object> getPersonsByFirestation(List<Long> stations);
}
