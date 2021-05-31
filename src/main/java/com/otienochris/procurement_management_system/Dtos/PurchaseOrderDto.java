package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDto {

    @NotNull
    private Integer purchaseRequisitionId;
    @NotNull(message = "please upload a RFI template")
    private MultipartFile rfiTemplate;
    @NotNull(message = "please upload a RFP template")
    private MultipartFile rfpTemplate;
    @NotNull(message = "please upload a terms and conditions file")
    private MultipartFile termsAndConditions;
    @NotNull
    @NotEmpty
    private String description;
}
