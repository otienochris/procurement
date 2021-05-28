package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationDto {
    @NotNull
    private Date deadlineDate;

    @NotNull
    private Integer purchaseOrderId;

    @NotNull
    private String message;
}
