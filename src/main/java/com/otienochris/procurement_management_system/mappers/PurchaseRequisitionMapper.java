package com.otienochris.procurement_management_system.mappers;
import com.otienochris.procurement_management_system.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface PurchaseRequisitionMapper {

    PurchaseRequisitionMapper INSTANCE = Mappers.getMapper(PurchaseRequisitionMapper.class);

    PurchaseRequisition purchaseRequisitionDtoToPurchaseRequisition(PurchaseRequisitionDto purchaseRequisitionDto);
}
