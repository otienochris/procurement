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
public class ApplicationObjectResponse {
    private Integer id;
    private String message;
    private Date dateCreated;
    private Integer purchaseOrderId;
    private String quotationDownloadUrl;
    private String informationDownloadUrl;
    private String supplierId;
}
