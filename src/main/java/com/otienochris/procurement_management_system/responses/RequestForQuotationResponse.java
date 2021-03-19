package com.otienochris.procurement_management_system.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestForQuotationResponse {

    private String description;
    private Date dateCreated;
    private Date dateModified;
    private Long purchaseOrderId;
    private String quotationDownloadUrl;
    private String termsAndConditionDownloadUrl;
}
