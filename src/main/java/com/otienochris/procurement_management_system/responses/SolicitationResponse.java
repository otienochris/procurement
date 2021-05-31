package com.otienochris.procurement_management_system.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationResponse {
    private Integer id;
    private Date dateCreated;
    private Date deadline;
    private Integer purchaseOrderId;
    private String message;
}
