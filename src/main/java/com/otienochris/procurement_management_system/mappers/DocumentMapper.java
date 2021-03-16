package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.models.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    Document documentDtoToDocument(DocumentDto documentDto);
    DocumentDto documentToDocumentDto(Document document);
    List<DocumentDto> documentsToDocumentDtos(List<Document> documents);
    List<Document> documentDtosToDocuments(List<DocumentDto> documentDtos);

}
