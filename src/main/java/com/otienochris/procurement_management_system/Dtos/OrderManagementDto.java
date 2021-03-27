package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.OMStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementDto {

    @Null
    private long id;
    @NotNull
    private OMStatus status;
    private String goodsReceivedNote;
    private String goodsReturnShipment;
    @NotNull
    private MultipartFile invoice;
}
