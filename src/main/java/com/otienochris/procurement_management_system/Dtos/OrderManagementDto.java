package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.OMStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderManagementDto {
    @NotNull
    private OMStatus status;
    private String goodsReceivedNote;
    private String goodsReturnShipment;
    @NotNull
    private MultipartFile invoice;
}
