package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

//    get all
    public List<Document> getAllDocuments (){
        return documentRepository.findAll();
    }

//    get by id
    public DocumentDto getById(Long id) {

//        todo remove the null
        if (documentRepository.findById(id).isEmpty())
            return null;
        return documentMapper.documentToDocumentDto(documentRepository.findById(id).get());

    }


//    save
    public Document uploadFile(DocumentDto documentDto, String title){
        return documentRepository.save(documentMapper.documentDtoToDocument(documentDto));
    }
//    update
    public void updateFile(Long id, MultipartFile multipartFile) {
        documentRepository.findById(id).ifPresent(document -> {
            document.setFileName(multipartFile.getOriginalFilename());
            try {
                document.setContent(multipartFile.getBytes());
            } catch (IOException e) {
            }
            documentRepository.save(document);
        });
    }
//    delete
    public void deleteFile(Long id){
        documentRepository.findById(id).ifPresent(document -> documentRepository.delete(document));
    }

}
