package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotNull
    private String email;

    @NotNull
    private String empId;

    @NotNull
    private String name;

    @NotNull
    private String password;
}
