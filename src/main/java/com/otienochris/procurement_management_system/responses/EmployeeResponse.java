package com.otienochris.procurement_management_system.responses;

import com.otienochris.procurement_management_system.models.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeResponse {

    private String email;
    private String employmentId;
    private String name;
    private String position;
    private Date dataCreated;
    private Date dateModified;
    private Boolean isActive;
    private Role roles;

}
