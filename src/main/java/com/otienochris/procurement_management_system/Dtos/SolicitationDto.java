package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.PurchaseOrder;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitationDto {
    @NotNull
    private Date deadlineDate;

    @NotNull
    private Long purchaseOrderId;
}