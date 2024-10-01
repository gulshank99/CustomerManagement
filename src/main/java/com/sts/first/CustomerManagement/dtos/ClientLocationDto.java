package com.sts.first.CustomerManagement.dtos;

import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientLocationDto {
    private Long clientLocationId;

//  private Long cityId;
    private String pincode;
    private String address1;
    @NotBlank(message = "HR contact person is required !!", groups = {CreateValidation.class })
    @Size(min = 3,max = 30,message = "Invalid HR contact person !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^[a-zA-Z]+$", message = "HR contact person must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
    private String hrContactPerson;

    @NotBlank(message = "Technical Person is required !!", groups = {CreateValidation.class })
    @Size(min = 3,max = 30,message = "Invalid Technical Person !!", groups = {CreateValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Technical Person must contain only alphabets.", groups = {CreateValidation.class, UpdateValidation.class})
    private String technicalPerson;

    @Min(value = 1000, message = "Phone number must be at least 4 digits long.", groups = {CreateValidation.class, UpdateValidation.class})
    @Digits(integer = 10, fraction = 0, message = "Phone number must be within 11 digits. !!", groups = {CreateValidation.class, UpdateValidation.class})
    @NotBlank(message = "HR MobileNumber is required !!", groups = {CreateValidation.class })
    @Pattern(regexp = "^(?:\\+?\\d{1,3}[- ]?)?\\d{8,12}$", message = "Mention Phone number with Country Code (Space not allowed).", groups = {CreateValidation.class, UpdateValidation.class})
    private String hrMobileNumber;

    @Min(value = 1000, message = "Phone number must be at least 4 digits long.", groups = {CreateValidation.class, UpdateValidation.class})
    @Digits(integer = 10, fraction = 0, message = "Phone number must be within 11 digits. !!", groups = {CreateValidation.class, UpdateValidation.class})
    @NotBlank(message = "CompanyLandline Number is required !!", groups = {CreateValidation.class })
    @Pattern(regexp = "^(?:\\+?\\d{1,3}[- ]?)?\\d{8,12}$", message = "Mention Phone number with Country Code (Space not allowed).", groups = {CreateValidation.class, UpdateValidation.class})
    private String companyLandline;

    @NotNull(message = "State required !!", groups = {CreateValidation.class })
    private MasterLocationDto state;

    @NotNull(message = " Client required !!", groups = {CreateValidation.class })
    private MasterClientDto client;

    @NotNull(message = "City required !!", groups = {CreateValidation.class })
    private MasterLocationDto cityId;
 
}
