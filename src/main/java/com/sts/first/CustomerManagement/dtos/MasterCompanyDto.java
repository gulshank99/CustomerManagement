package com.sts.first.CustomerManagement.dtos;

import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MasterCompanyDto {

    private Long companyId;

    @NotBlank(
            message = "Company details is required !!",
            groups = {CreateValidation.class})
    @Size(
            min = 3,
            max = 30,
            message = "Invalid Company  Name !!",
            groups = {CreateValidation.class, UpdateValidation.class})
    @Pattern(
            regexp = "^[a-zA-Z]+[^\\w]*[a-zA-Z0-9]*$",
            message = "Domain must be valid.",
            groups = {CreateValidation.class, UpdateValidation.class})
    private String companyName;
}
