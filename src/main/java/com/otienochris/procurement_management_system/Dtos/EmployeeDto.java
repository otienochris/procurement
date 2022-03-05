package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.EmployeePositionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String empId;

    @NotNull
    private String name;

    @NotNull
    private EmployeePositionEnum position;

    @NotNull
    private String password;
}
