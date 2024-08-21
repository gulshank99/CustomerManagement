package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.MasterTechnologyDto;
import com.sts.first.CustomerManagement.services.MasterTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class MasterTechnologyController {

    @Autowired
    private MasterTechnologyService masterTechnologyService;

    @PostMapping
    public ResponseEntity<MasterTechnologyDto> createTechnology(@RequestBody MasterTechnologyDto masterTechnologyDto) {
        return ResponseEntity.ok(masterTechnologyService.createTechnology(masterTechnologyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterTechnologyDto> updateTechnology(@PathVariable Long id, @RequestBody MasterTechnologyDto masterTechnologyDto) {
        return ResponseEntity.ok(masterTechnologyService.updateTechnology(id, masterTechnologyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        masterTechnologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterTechnologyDto> getTechnologyById(@PathVariable Long id) {
        return ResponseEntity.ok(masterTechnologyService.getTechnologyById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterTechnologyDto>> getAllTechnologies() {
        return ResponseEntity.ok(masterTechnologyService.getAllTechnologies());
    }
}
