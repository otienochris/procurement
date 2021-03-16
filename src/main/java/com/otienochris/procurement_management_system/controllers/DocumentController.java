package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.mappers.DocumentMapper;
import com.otienochris.procurement_management_system.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping("/download")
    public void downloadFile(@PathParam("id") Long id){

        /*documentRepo.findById(id).
                ifPresentOrElse(
                        document -> {
                            response.setContentType("application/octet-stream");
                            String headerKey =  "Content-Disposition";
                            String headerValue = "attachment; filename=" + document.getName();
                            response.setHeader(headerKey, headerValue);

                            try {
                                ServletOutputStream outputStream = response.getOutputStream();
                                outputStream.write(document.getContent());
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        },
                        () -> {
                            try {
                                throw new Exception("Could not find document with id : " + id);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });*/
    }
}
