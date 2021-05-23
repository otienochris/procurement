package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.POStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDto {

    @NotNull
    private UUID purchaseRequisitionId;
    @NotNull(message = "please upload a RFI template")
    private MultipartFile rfiTemplate;
    @NotNull(message = "please upload a RFP template")
    private MultipartFile rfpTemplate;
}
