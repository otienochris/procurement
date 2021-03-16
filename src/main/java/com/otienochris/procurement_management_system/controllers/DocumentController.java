package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.DocumentDto;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(documentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Document> allDocs(){
//        todo return a list of DocumentDtos
        return documentService.getAllDocuments();
    }

    @PostMapping("/upload/{title}")
    public ResponseEntity<Document> uploadFile(@RequestBody DocumentDto documentDto,
                                               @PathVariable("title") String title) {
        return new ResponseEntity<>(documentService.uploadFile(documentDto, title), HttpStatus.CREATED);
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
