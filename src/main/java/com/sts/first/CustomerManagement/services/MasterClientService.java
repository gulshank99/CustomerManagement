package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.MasterClientDto;

import java.util.List;

public interface MasterClientService {
    MasterClientDto createClient(MasterClientDto clientDto);
    MasterClientDto updateClient(Long clientId, MasterClientDto clientDto);
    void deleteClient(Long clientId);
    MasterClientDto getClientById(Long clientId);
    List<MasterClientDto> getAllClients();
}
