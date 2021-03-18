package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public List<DocumentDto> getAllDocuments (){
        ArrayList<Document> documents = new ArrayList<>(documentRepository.findAll());
        return documentMapper.documentsToDocumentDtos(documents);
    }


    public DocumentDto getById(Long id) {

        if (documentRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Item with id: " + id + " not found!");
        Document document = documentRepository.findById(id).get();
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(document.getFileName())
                .toUriString();
        DocumentDto documentDto = documentMapper.documentToDocumentDto(documentRepository.findById(id).get());
        documentDto.setUrl(url);

        return documentDto;

    }

    public DocumentDto uploadFile(DocumentDto documentDto, String type){

        documentDto.setType(type); // set the type of the document

        Document savedDocument = documentRepository.save(documentMapper.documentDtoToDocument(documentDto));

        // form me download url from the doc's name
        String name = StringUtils.cleanPath(Objects.requireNonNull(savedDocument.getFileName()));
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(name)
                .toUriString();

        DocumentDto returnedDocumentDto = documentMapper.documentToDocumentDto(savedDocument);

        // set the download url
        returnedDocumentDto.setUrl(url);
        return returnedDocumentDto;
    }

    public Document download(String fileName){
        return documentRepository.findByFileName(fileName);
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

}
