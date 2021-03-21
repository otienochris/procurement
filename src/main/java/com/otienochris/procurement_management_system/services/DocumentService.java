package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.responses.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.NoSuchFileException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public List<DocumentResponse> getAllDocuments (){
        List<DocumentResponse> responses = new ArrayList<>();
        documentRepository.findAll().forEach(document -> {
            responses.add(createResponse(document));
        });
        return responses;
    }


    public DocumentResponse getById(Long id) throws NoSuchFileException {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty())
            throw new NoSuchFileException("Item with id: " + id + " not found!");
        return createResponse(document.get());

    }

    public DocumentResponse uploadFile(DocumentDto documentDto, String type){
        documentDto.setType(type); // set the type of the document
        Document savedDocument = documentRepository.save(documentMapper.documentDtoToDocument(documentDto));
        return createResponse(savedDocument);
    }

    public Optional<Document> download(String fileName){
        return documentRepository.findOneByFileName(fileName);
    }

    public void updateFile(Long id, Document newDocument) {
        documentRepository.findById(id).ifPresent(document -> {
            document.setDateCreated(null);
            document.setDateModified(null);
            document.setFileName(newDocument.getFileName());
            document.setContent(newDocument.getContent());
            documentRepository.save(document);
        });
    }

    public void deleteFile(Long id){
        documentRepository.findById(id).ifPresent(documentRepository::delete);
    }

//    todo ensure file name are unique
    public DocumentResponse downloadByFileName(String fileName) throws NoSuchFileException {
        Optional<Document> document = documentRepository.findOneByFileName(fileName);
        if (document.isEmpty())
            throw new NoSuchFileException("Item with name: " + fileName + " not found!");
        return createResponse(document.get());

    }

    private DocumentResponse createResponse(Document document){
        String name = StringUtils.cleanPath(
                Objects.requireNonNull(document.getFileName()));
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(name)
                .toUriString();
        return DocumentResponse.builder()
                    .type(document.getType())
                    .dateCreated(document.getDateCreated())
                    .dateModified(document.getDateModified())
                    .fileName(document.getFileName())
                    .url(downloadUrl)
                    .build();
    }
}
