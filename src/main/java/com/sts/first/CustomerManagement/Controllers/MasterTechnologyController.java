package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.MasterTechnologyDto;
import com.sts.first.CustomerManagement.services.MasterTechnologyService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class MasterTechnologyController {

    @Autowired
    private MasterTechnologyService masterTechnologyService;

    @PostMapping
    public ResponseEntity<MasterTechnologyDto> createTechnology(@Validated(CreateValidation.class) @RequestBody MasterTechnologyDto masterTechnologyDto) {
        return ResponseEntity.ok(masterTechnologyService.createTechnology(masterTechnologyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterTechnologyDto> updateTechnology(@PathVariable Long id, @Validated(CreateValidation.class) @RequestBody MasterTechnologyDto masterTechnologyDto) {
        return ResponseEntity.ok(masterTechnologyService.updateTechnology(id, masterTechnologyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteTechnology(@PathVariable Long id) {
        masterTechnologyService.deleteTechnology(id); ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
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
