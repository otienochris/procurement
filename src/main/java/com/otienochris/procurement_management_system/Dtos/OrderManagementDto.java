package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.enums.OMStatusEnum;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderManagementDto {
    @NotNull
    private OMStatusEnum status;
    private String goodsReceivedNote;
    private String goodsReturnShipment;
    @NotNull
    private MultipartFile invoice;
}
