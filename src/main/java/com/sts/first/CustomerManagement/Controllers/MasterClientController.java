package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.MasterClientDto;
import com.sts.first.CustomerManagement.services.MasterClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class MasterClientController {

    @Autowired
    private MasterClientService masterClientService;

    @PostMapping
    public ResponseEntity<MasterClientDto> createClient(@Valid @RequestBody MasterClientDto masterClientDto) {
        return ResponseEntity.ok(masterClientService.createClient(masterClientDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterClientDto> updateClient(@PathVariable Long id, @RequestBody MasterClientDto masterClientDto) {
        return ResponseEntity.ok(masterClientService.updateClient(id, masterClientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteClient(@PathVariable Long id) {
        masterClientService.deleteClient(id); ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(masterClientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterClientDto>> getAllClients() {
        return ResponseEntity.ok(masterClientService.getAllClients());
    }
}
