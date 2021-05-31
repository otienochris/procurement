package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    @NotNull
    @NotEmpty
    private String kRA;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
