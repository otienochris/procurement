package com.otienochris.procurement_management_system.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentHeadResponse {
    private String name;
    private String email;
    private String empId;
    private String departmentId;
    private boolean isActive;
}
