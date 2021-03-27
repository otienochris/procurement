package com.otienochris.procurement_management_system.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequisitionResponse {

    private long id;
    private String needDocumentUrl;
    private String acquisitionDocumentUrl;
    private String analysisDocumentUrl;
    private String emergencyDocumentUrl;
}
