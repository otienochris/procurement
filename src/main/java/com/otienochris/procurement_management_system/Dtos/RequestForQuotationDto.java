package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestForQuotationDto {

    @NotBlank
    private String message;

    @NotNull
    private MultipartFile quotationDocument;

    @NotNull
    private MultipartFile termsAndConditions;

    @NotNull
    @PositiveOrZero
    private Long purchaseOrderId;
}
