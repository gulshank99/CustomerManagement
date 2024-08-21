package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.MasterDomainDto;
import com.sts.first.CustomerManagement.services.MasterDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domains")
public class MasterDomainController {

    @Autowired
    private MasterDomainService masterDomainService;

    @PostMapping
    public ResponseEntity<MasterDomainDto> createDomain(@RequestBody MasterDomainDto masterDomainDto) {
        return ResponseEntity.ok(masterDomainService.createDomain(masterDomainDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterDomainDto> updateDomain(@PathVariable Long id, @RequestBody MasterDomainDto masterDomainDto) {
        return ResponseEntity.ok(masterDomainService.updateDomain(id, masterDomainDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
        masterDomainService.deleteDomain(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterDomainDto> getDomainById(@PathVariable Long id) {
        return ResponseEntity.ok(masterDomainService.getDomainById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterDomainDto>> getAllDomains() {
        return ResponseEntity.ok(masterDomainService.getAllDomains());
    }
}
