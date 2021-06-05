package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.enums.OMStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementApprovalObj {
        private Integer id;
        private String target;
        private OMStatusEnum status;
    }
