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
    @NotNull(message = "please upload a need document")
    private MultipartFile needDocument;
    @NotNull(message = "please upload an emergency document")
    private MultipartFile emergencyDocument;
    @NotNull(message = "please upload an acquisition document")
    private MultipartFile acquisitionDocument;
    @NotNull(message = "please upload an analysis document")
    private MultipartFile analysisDocument;
}
