package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.MasterLocationDto;

import java.util.List;

public interface MasterLocationService {
    MasterLocationDto createLocation(MasterLocationDto locationDto);
    MasterLocationDto updateLocation(Long id, MasterLocationDto locationDto);
    void deleteLocation(Long id);
    MasterLocationDto getLocationById(Long id);
    List<MasterLocationDto> getAllLocations();
}
