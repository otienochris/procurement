package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestForQuotationDto {
    private String message;
    private MultipartFile quotationDocument;
    private MultipartFile termsAndConditions;
    private Long purchaseOrderId;
}
