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
public class PurchaseOrderResponse {
    private Long id;
    private String rfiTemplateDownloadUrl;
    private String rfpTemplateDownloadUrl;
    private String status;
    private Date dataCreated;
    private Date dateModified;
}
