package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/all")
    public List<Document> allDocs(){
        return documentService.getAllDocuments();
    }

    @PostMapping("/upload/{title}")
    public ResponseEntity<Document> uploadFile(@RequestBody MultipartFile multipartFile,
                                               @PathVariable("title") String title) throws IOException {
        return new ResponseEntity<>(documentService.uploadFile(multipartFile, title), HttpStatus.CREATED);
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
