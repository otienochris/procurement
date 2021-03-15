package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.models.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.Arrays;

@Component
public class MultipartDocumentMapper {
    protected Document multipartFileToDocument1(MultipartFile multipartFile) throws IOException {
        if ( multipartFile == null ) {
            return null;
        }

        Document.DocumentBuilder document = Document.builder();

        byte[] content = multipartFile.getBytes();
        if ( content != null ) {
            document.content( Arrays.copyOf( content, content.length ) );
        }
        document.fileName( multipartFile.getOriginalFilename() );

        return document.build();
    }
}
