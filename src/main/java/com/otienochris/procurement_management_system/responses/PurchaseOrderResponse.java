package com.otienochris.procurement_management_system.responses;

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
public class PurchaseOrderResponse {
    private Integer id;
    private String rfiTemplateDownloadUrl;
    private String rfpTemplateDownloadUrl;
    private String termsAndConditionsDownloadUrl;
    private String status;
    private Date dataCreated;
    private Integer purchaseRequisitionId;
    private String description;
}
