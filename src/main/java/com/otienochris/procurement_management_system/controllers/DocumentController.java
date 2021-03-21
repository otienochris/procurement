package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.responses.DocumentResponse;
import com.otienochris.procurement_management_system.services.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getById(@PathVariable("id") Long id) throws NoSuchFileException {
        log.info("A get request to retrieve a document with id: " + id);
        return new ResponseEntity<>(documentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<DocumentResponse> getByName(@PathVariable("fileName") String fileName) throws NoSuchFileException {
        return new ResponseEntity<>(documentService.downloadByFileName(fileName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<DocumentResponse> allDocs(){
        log.info("Getting all documents: in the document controller");
        return documentService.getAllDocuments();
    }

    @PostMapping("/upload/{title}")
    public ResponseEntity<DocumentResponse> uploadFile(@RequestBody DocumentDto documentDto,
                                                       @PathVariable("title") String title) {
        log.info("Uploading a document: in the document controller");
        return new ResponseEntity<>(documentService.uploadFile(documentDto, title), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") Long id){
        log.info("Deleting a document: in the document controller");
        documentService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFile(@PathVariable("id") Long id, @Validated DocumentDto documentDto){
        documentService.updateFile(id, documentMapper.documentDtoToDocument(documentDto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request){

        // retrieved document from the db
        Optional<Document> retrievedDocument = documentService.download(fileName);
        if (retrievedDocument.isEmpty())
            throw new NoSuchElementException("A file named: [" + fileName + "] does not exist");
        Document document = retrievedDocument.get(); // if document exists, proceed

        // retrieve the document's name
        String name = document.getFileName();
        // form mime type using the name
        String mimeType = request.getServletContext().getMimeType(name);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                // render to the browser
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=" + name)
                .body(document.getContent());
    }
}
