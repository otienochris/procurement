package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.models.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    protected MultipartFile multipartFileToDocument1(Document document) throws IOException {
        if ( document == null ) {
            return null;
        }

        return new MultipartFile() {
            @Override
            public String getName() {
                return document.getFileName();
            }

            @Override
            public String getOriginalFilename() {
                return document.getFileName();
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return document.getContent();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
    }
}
