package com.otienochris.procurement_management_system.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    @NotNull
    private String supplierId;
    @NotNull
    private Integer purchaseOrderId;
    @NotNull
    private Date expiryDate;
    @NotNull
    private MultipartFile contractDocument;
}
