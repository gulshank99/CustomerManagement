package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterTechnologyDto {
    private Long techId;

  @NotBlank(message = "Technology is required !!", groups = CreateValidation.class)
  @Pattern(
      regexp = "^[a-zA-Z]+[^\\w]*[a-zA-Z0-9]*$",
      message = "Technology should be valid",
      groups = {CreateValidation.class, UpdateValidation.class})
  private String technology;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate insertedOn;
}
