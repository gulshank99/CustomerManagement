package com.sts.first.CustomerManagement.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterClientDto {

    private Long clientId;

    @NotBlank(message = "Client name is required !!", groups = {CreateValidation.class })
    @Size(min = 3,max = 30,message = "Invalid Client Name !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Client Name must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
    private String clientName;

    @NotBlank(message = "Client Head Office is required !!", groups = {CreateValidation.class })
    @Size(min = 3,max = 30,message = "Invalid Client Head Office !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Client Head Office must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
    private String clientHo;

//    @NotNull(message = "Location details are required !!", groups = {CreateValidation.class })
//    private List<ClientLocationDto> clientLocations;
//
//    @NotNull(message = "Job details are required !!", groups = {CreateValidation.class })
//    private List<ClientJobDto> clientJobs;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;


}