package com.example.usermanagementbackend.controller;

import com.example.usermanagementbackend.model.Image;
import com.example.usermanagementbackend.model.response.FileResponse;
import com.example.usermanagementbackend.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    // 1. Upload file
    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }

    // 2. Xem thông tin file
    @GetMapping(value = "files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image image = fileService.readFile(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
    }
}
