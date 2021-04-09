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
public class RequestForQuotationResponse {

    private UUID id;
    private String description;
    private Date dateCreated;
    private Date dateModified;
    private UUID purchaseOrderId;
    private String quotationDownloadUrl;
    private String termsAndConditionDownloadUrl;
}
