package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;


@Mapper(uses = MultipartDocumentMapper.class)
public interface PurchaseOrderMapper {
    @Mapping(source = "rfiTemplate", target = "rFITemplate")
    @Mapping(source = "rfpTemplate", target = "rFPTemplate")
    @Mapping(source = "status", target = "status")
    PurchaseOrder purchaseOrderDtoToPurchaseOrder(PurchaseOrderDto purchaseOrderDto) throws IOException;
//    PurchaseOrderDto purchaseOrderToPurchaseOrderDto(PurchaseOrder purchaseOrder);
}
