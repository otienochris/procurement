package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequisitionDto {
    @Null
    private long id;
    @NotNull
    private MultipartFile needDocument;
    @NotNull
    private MultipartFile emergencyDocument;
    @NotNull
    private MultipartFile acquisitionDocument;
    @NotNull
    private MultipartFile analysisDocument;
}
