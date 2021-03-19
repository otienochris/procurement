package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    PurchaseOrder purchaseOrderDtoToPurchaseOrder(PurchaseOrderDto purchaseOrderDto);
}
