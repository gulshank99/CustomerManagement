package com.sts.first.CustomerManagement.dtos;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterClientDto {
    private Long clientId;
    private String clientName;
    private String address;
    private String hrContactPerson;
    private String technicalPerson;
    private Long primaryMobile;
    private Long secondaryNumber;
    private LocalDateTime insertedOn;
    private MasterLocationDto location;
}