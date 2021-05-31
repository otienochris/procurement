package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentHeadDto {
    @Email(message = "Enter a valid email")
    @NotNull(message = "Email field is required")
    private String email;
    @NotNull
    @NotEmpty
    private String empId;
    @NotNull
    @NotEmpty
    private String departmentId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String password;
}
