package com.api.book.helper;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    //public final String UPLOAD_DIR="src/main/resources/static/image"; // This is for Static File Upload
    public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath(); // This is for Dynamic File Upload

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file) {
        boolean f = false;
        try{
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            System.out.println(UPLOAD_DIR+File.separator+file.getOriginalFilename());
            System.out.println("here");
            f = true;
        } catch (Exception e) {

        }

        return f;
    }
}
