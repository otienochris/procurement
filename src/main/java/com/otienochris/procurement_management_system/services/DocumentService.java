package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    public List<DocumentDto> getAllDocuments (){
        ArrayList<Document> documents = new ArrayList<>(documentRepository.findAll());
        return documentMapper.documentsToDocumentDtos(documents);
    }


    public DocumentDto getById(Long id) {

        if (documentRepository.findById(id).isEmpty())
            throw new NoSuchElementException("Item with id: " + id + " not found!");
        return documentMapper.documentToDocumentDto(documentRepository.findById(id).get());

    }

    public DocumentDto uploadFile(DocumentDto documentDto, String title){
        documentDto.setType(title);
        Document newDocument = documentMapper.documentDtoToDocument(documentDto);
        return documentMapper.documentToDocumentDto(documentRepository.save(newDocument));
    }

    public Document updateFile(Long id, Document newDocument) {
        documentRepository.findById(id).ifPresent(document -> {
            document.setDateCreated(null);
            document.setDateModified(null);
            document.setFileName(newDocument.getFileName());
            document.setContent(newDocument.getContent());
            documentRepository.save(document);
        });

        return documentRepository.findById(id).get();
    }

    public void deleteFile(Long id){
        documentRepository.findById(id).ifPresent(document -> documentRepository.delete(document));
    }

}
