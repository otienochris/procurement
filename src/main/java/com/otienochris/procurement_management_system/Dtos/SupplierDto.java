package com.otienochris.procurement_management_system.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDto {
    private String kRA;
    private String name;
    private String description;
    private String password;
}
