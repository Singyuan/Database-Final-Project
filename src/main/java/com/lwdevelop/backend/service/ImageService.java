package com.lwdevelop.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    public void saveUploadedFiles(MultipartFile multipartFile) throws IOException {
        byte[] bytes =multipartFile.getBytes();
        Path path = Paths.get("src/main/resources/static/" + multipartFile.getOriginalFilename());
        Files.write(path, bytes);
    }
}
