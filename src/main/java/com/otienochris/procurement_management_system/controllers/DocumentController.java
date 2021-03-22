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

    @GetMapping("/{fileName}")
    public ResponseEntity<DocumentResponse> findByFileName(@PathVariable("fileName") String fileName){
        log.info("A get request to retrieve a document named : " + fileName);
        return new ResponseEntity<>(documentService.findByFileName(fileName), HttpStatus.OK);
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

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable("fileName") String filename){
        log.info("Deleting a document: in the document controller");
        documentService.deleteFile(filename);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{fileName}")
    public ResponseEntity<?> updateFile(@PathVariable("fileName") String fileName, @Validated DocumentDto documentDto){
        documentService.updateFile(fileName, documentMapper.documentDtoToDocument(documentDto));
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
