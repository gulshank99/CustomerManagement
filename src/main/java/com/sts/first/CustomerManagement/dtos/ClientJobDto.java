package com.sts.first.CustomerManagement.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClientJobDto {

        private Long jobId;

//        @NotBlank(message = "Job code required !!", groups = {CreateValidation.class })
        @Size(min = 2,max = 30,message = "Invalid Job Code !!", groups = {CreateValidation.class, UpdateValidation.class})
        private String jobCode;

        @NotBlank(message = "Job Description required !!", groups = {CreateValidation.class })
        @Size(min = 2,max = 30,message = "Invalid Job Description !!", groups = {CreateValidation.class, UpdateValidation.class})
        private String jd;

        @NotBlank(message = "HR contact person is required !!", groups = {CreateValidation.class })
        @Size(min = 3,max = 30,message = "Invalid HR contact person !!", groups = {CreateValidation.class, UpdateValidation.class})
        private String jobTitle;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate createdOn;


        @NotBlank(message = "Job status is required !!", groups = {CreateValidation.class })
        @Size(min = 3,max = 6,message = "Invalid Job status !!", groups = {CreateValidation.class, UpdateValidation.class})
        @Pattern(regexp = "Active|Closed|OnHold", message = "Job status should be: Active|Closed|OnHold", groups = {CreateValidation.class, UpdateValidation.class})
        private String isJobActive;

        @NotBlank(message = "Job post type is required !!", groups = {CreateValidation.class })
        @Pattern(regexp = "Replacement|New", message = "Job Post Type should be: Replacement|New", groups = {CreateValidation.class, UpdateValidation.class})
        private String jobPostType;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate postCreatedOn;

        @NotBlank(message = "Inserted By is required !!", groups = {CreateValidation.class })
        @Size(min = 3,max = 30,message = "Invalid Name !!", groups = {CreateValidation.class, UpdateValidation.class})
        private String insertedBy;


//        @NotNull(message = "Techs required !!", groups = {CreateValidation.class })
//        private List<ClientJobTechDto> clientJobTechs;

        @NotNull(message = "Client required !!", groups = {CreateValidation.class })
        private MasterClientDto client;
}
