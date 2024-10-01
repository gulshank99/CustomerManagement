package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ClientLocationDto;
import com.sts.first.CustomerManagement.services.ClientLocationService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-locations")
public class ClientLocationController {

    @Autowired
    private ClientLocationService clientLocationService;

    @PostMapping
    public ResponseEntity<ClientLocationDto> createLocation(@Validated(CreateValidation.class) @RequestBody ClientLocationDto locationDto) {
        ClientLocationDto createdLocation = clientLocationService.createLocation(locationDto);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<ClientLocationDto> updateLocation(
            @PathVariable Long locationId,
            @Validated(UpdateValidation.class)  @RequestBody  ClientLocationDto locationDto) {
        ClientLocationDto updatedLocation = clientLocationService.updateLocation(locationId, locationDto);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
        clientLocationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<ClientLocationDto> getLocationById(@PathVariable Long locationId) {
        ClientLocationDto locationDto = clientLocationService.getLocationById(locationId);
        return ResponseEntity.ok(locationDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientLocationDto>> getAllLocations() {
        List<ClientLocationDto> locations = clientLocationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
}
