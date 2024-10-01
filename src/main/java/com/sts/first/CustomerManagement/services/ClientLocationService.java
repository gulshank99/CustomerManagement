package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ClientLocationDto;

import java.util.List;

public interface ClientLocationService {
    ClientLocationDto createLocation(ClientLocationDto locationDto);
    ClientLocationDto updateLocation(Long locationId, ClientLocationDto locationDto);
    void deleteLocation(Long locationId);
    ClientLocationDto getLocationById(Long locationId);
    List<ClientLocationDto> getAllLocations();
}