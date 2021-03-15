package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.exception_handlers.ResourceNotFoundException;
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

//    get all
    public List<Document> getAllDocuments (){
        return documentRepository.findAll();
    }

//    get by id
    public Document getById(Long id) throws ResourceNotFoundException {
        return documentRepository.findById(id).orElseThrow( () ->
            {throw new IllegalArgumentException("Item not found!");}
        );
    }


//    save
    public Document uploadFile(MultipartFile multipartFile, String title) throws IOException {
        Document document = Document.builder()
                .fileName(multipartFile.getOriginalFilename())
                .content(multipartFile.getBytes())
                .title(title)
                .build();
        return documentRepository.save(document);
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
