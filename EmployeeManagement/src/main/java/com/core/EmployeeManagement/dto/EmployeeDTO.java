package com.core.EmployeeManagement.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String contactEmail;

}
