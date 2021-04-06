package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationDto {
    @NotNull
    private String deadlineDate;

    @NotNull
    private UUID purchaseOrderId;
}
