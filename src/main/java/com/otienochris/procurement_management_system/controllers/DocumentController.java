package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(documentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<DocumentDto> allDocs(){
        return documentService.getAllDocuments();
    }

    @PostMapping("/upload/{title}")
    public ResponseEntity<DocumentDto> uploadFile(@RequestBody DocumentDto documentDto,
                                               @PathVariable("title") String title) {
        return new ResponseEntity<>(documentService.uploadFile(documentDto, title), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable("id") Long id){
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
        Document retrievedDocument = documentService.download(fileName);

        // retrieve the document's name
        String name = retrievedDocument.getFileName();
        // form mime type using the name
        String mimeType = request.getServletContext().getMimeType(name);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                // render to the browser
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=" + name)
                .body(retrievedDocument.getContent());
    }
}
