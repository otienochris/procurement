package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseRequisitionDto {
    @NotNull
    private MultipartFile needDocument;
    @NotNull
    private MultipartFile emergencyDocument;
    @NotNull
    private MultipartFile acquisitionDocument;
    @NotNull
    private MultipartFile analysisDocument;
}
