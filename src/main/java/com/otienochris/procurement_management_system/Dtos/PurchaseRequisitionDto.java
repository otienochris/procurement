package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequisitionDto {

    @NotNull(message = "please describe the purchase requisition")
    private String description;
    private Integer departmentId;
    private POStatusEnum status;
    @NotNull(message = "please upload a need document")
    private MultipartFile needDocument;
    @NotNull(message = "please upload an emergency document")
    private MultipartFile emergencyDocument;
    @NotNull(message = "please upload an acquisition document")
    private MultipartFile acquisitionDocument;
    @NotNull(message = "please upload an analysis document")
    private MultipartFile analysisDocument;
}
