package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

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
//    @PositiveOrZero
    private Integer purchaseOrderId;
}
