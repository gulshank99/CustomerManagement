package com.sts.first.CustomerManagement.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterDomainDto {
    private Long domainId;

  @NotBlank(
      message = "Domain details is required !!",
      groups = {CreateValidation.class})
  @Size(
      min = 3,
      max = 30,
      message = "Invalid Domain Name !!",
      groups = {CreateValidation.class, UpdateValidation.class})
  @Pattern(
      regexp = "^[a-zA-Z]+[^\\w]*[a-zA-Z0-9]*$",
      message = "Domain must be valid.",
      groups = {CreateValidation.class, UpdateValidation.class})
  private String domainDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;
}