package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.RFIDto;
import com.otienochris.procurement_management_system.models.RFI;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface RFIMapper {
    RFIMapper INSTANCE = Mappers.getMapper(RFIMapper.class);

    RFI rfiDtoToRfi(RFIDto rfiDto);
}
