package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.models.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;

@Mapper(uses = MultipartDocumentMapper.class)
public interface DocumentMapper {

    /*@Mapping(source = "id", target = "id",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
   @Mapping(source = "title", target = "title",
           nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)*/
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "dateCreated", target = "dateCreated")
    @Mapping(source = "fileName", target = "fileName")
    Document documentDtoToDocument(DocumentDto documentDto) throws IOException;


//    DocumentDto documentToDocumentDto(Document document);
}
