package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationObjectDto {
    @NotBlank
    private String message;

    @NotNull
    private MultipartFile quotationDocument;
    
    @NotNull
    private MultipartFile informationDocument;

    @NotNull
    private Integer purchaseOrderId;

    private String supplierId;
}
