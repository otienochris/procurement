package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.ApplicationObjectDto;
import com.otienochris.procurement_management_system.models.ApplicationObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface ApplicationObjectMapper {

    ApplicationObjectMapper INSTANCE = Mappers.getMapper(ApplicationObjectMapper.class);
    ApplicationObject applicationDtoToApplication(ApplicationObjectDto applicationObjectDto);
}
