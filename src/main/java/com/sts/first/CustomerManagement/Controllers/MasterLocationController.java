package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.services.MasterLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class MasterLocationController {

    @Autowired
    private MasterLocationService masterLocationService;

    @PostMapping
    public ResponseEntity<MasterLocationDto> createLocation(@RequestBody MasterLocationDto masterLocationDto) {
        return ResponseEntity.ok(masterLocationService.createLocation(masterLocationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterLocationDto> updateLocation(@PathVariable Long id, @RequestBody MasterLocationDto masterLocationDto) {
        return ResponseEntity.ok(masterLocationService.updateLocation(id, masterLocationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        masterLocationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterLocationDto> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(masterLocationService.getLocationById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterLocationDto>> getAllLocations() {
        return ResponseEntity.ok(masterLocationService.getAllLocations());
    }
}
