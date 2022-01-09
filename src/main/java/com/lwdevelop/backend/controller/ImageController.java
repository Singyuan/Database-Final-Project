package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.payload.response.ImageResponse;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/images")
@PreAuthorize("hasRole('SELLER')")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok(new MessageResponse("Please select a file!"));
        }
        try {
            imageService.saveUploadedFiles(multipartFile);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("");
        }
        ImageResponse.Image image = new ImageResponse.Image("src/main/resources/static/" + multipartFile.getOriginalFilename());
        return ResponseEntity.ok(new ImageResponse(HttpStatus.OK.value(), "Your picture has been successfully loaded", image));
    }
}
