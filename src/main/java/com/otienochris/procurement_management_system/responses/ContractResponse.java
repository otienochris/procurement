package com.otienochris.procurement_management_system.responses;

import com.otienochris.procurement_management_system.models.enums.ContractStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {
    private String supplierId;
    private Date expiryDate;
    private List<ContractStatusEnum> status;
    private String contractDocumentUrl;
}