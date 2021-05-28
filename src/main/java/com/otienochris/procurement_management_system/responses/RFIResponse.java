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
public class RFIResponse {
    private Integer id;
    private String rfiUrl;
    private Integer purchaseOrderId;
    private String description;
    private Date dateCreated;
}
