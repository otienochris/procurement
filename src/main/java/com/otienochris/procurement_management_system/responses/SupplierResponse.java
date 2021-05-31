package com.otienochris.procurement_management_system.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierResponse {
    private String kRA;
    private String name;
    private String description;
    private Boolean isAccountActive;
    private String email;
}
