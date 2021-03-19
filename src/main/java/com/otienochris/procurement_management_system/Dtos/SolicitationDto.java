package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationDto {
    @NotNull
    private String deadlineDate;

    @NotNull
    private Long purchaseOrderId;
}
