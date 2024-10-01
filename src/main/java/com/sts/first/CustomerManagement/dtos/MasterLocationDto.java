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
public class MasterLocationDto {
    private Long locationId;

  @NotBlank(message = "Location Details Required", groups = CreateValidation.class)
  @Pattern(
      regexp = "^[a-zA-Z]+[^\\w]*[a-zA-Z0-9]*$",
      message = "Location Details must be valid.",
      groups = {CreateValidation.class, UpdateValidation.class})
  @Size(
      min = 3,
      max = 30,
      message = "Invalid Location Details !!",
      groups = {CreateValidation.class, UpdateValidation.class})
  private String locationDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;
}