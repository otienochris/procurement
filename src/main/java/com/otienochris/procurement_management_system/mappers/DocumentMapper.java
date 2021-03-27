package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.models.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    Document documentDtoToDocument(DocumentDto documentDto);

}
