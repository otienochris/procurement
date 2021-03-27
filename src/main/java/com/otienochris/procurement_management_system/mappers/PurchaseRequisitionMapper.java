package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.PurchaseRequisitionDto;
import com.otienochris.procurement_management_system.models.PurchaseRequisition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface PurchaseRequisitionMapper {

    PurchaseRequisitionMapper INSTANCE = Mappers.getMapper(PurchaseRequisitionMapper.class);

    PurchaseRequisition purchaseRequisitionDtoToPurchaseRequisition(PurchaseRequisitionDto purchaseRequisitionDto);
}
