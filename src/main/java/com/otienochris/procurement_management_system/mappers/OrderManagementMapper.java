package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.OrderManagementDto;
import com.otienochris.procurement_management_system.models.OrderManagement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface OrderManagementMapper {
    OrderManagementMapper INSTANCE = Mappers.getMapper(OrderManagementMapper.class);

    OrderManagement orderManagementDtoToOrderManagement(OrderManagementDto orderManagementDto);
}
