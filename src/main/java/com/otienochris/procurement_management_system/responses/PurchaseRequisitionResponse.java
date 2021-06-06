package com.otienochris.procurement_management_system.responses;

import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequisitionResponse {

    private Integer id;
    private Integer departmentId;
    private POStatusEnum status;
    private String needDocumentUrl;
    private String acquisitionDocumentUrl;
    private String analysisDocumentUrl;
    private String emergencyDocumentUrl;
    private String description;
    private Date dateCreated;
}
