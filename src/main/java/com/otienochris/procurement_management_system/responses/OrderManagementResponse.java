package com.otienochris.procurement_management_system.responses;

import com.otienochris.procurement_management_system.models.enums.OMStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderManagementResponse {
    private Integer id;
    private Integer purchaseOrderId;
    private String invoiceUrl;
    private String goodsReceivedNoteUrl;
    private String goodsReturnedShipmentUrl;
    private OMStatusEnum supplierApproval;
    private OMStatusEnum departmentHeadApproval;
    private OMStatusEnum storeManagerApproval;
    private OMStatusEnum procurementOfficerApproval;
}
