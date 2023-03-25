package com.example.usermanagementbackend.service;

import com.example.usermanagementbackend.exception.BadRequestException;
import com.example.usermanagementbackend.exception.NotFoundException;
import com.example.usermanagementbackend.model.Image;
import com.example.usermanagementbackend.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.example.usermanagementbackend.model.response.FileResponse;
@Service
public class FileService {
    private final ImageRepository imageRepository;

    public FileService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public FileResponse uploadFile(MultipartFile file) {
//        validateFile(file);

        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .createAt(LocalDateTime.now())
                    .build();
            imageRepository.save(image);

            return new FileResponse("/api/v1/files/" + image.getId());
        } catch (IOException e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }

    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // Tên file không được trống
        if(fileName == null || fileName.isEmpty()) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // Type file có nằm trong ds cho phép hay không
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new BadRequestException("Type file không hợp lệ");
        }

        // Kích thước size có trong phạm vi cho phép không
        double fileSize = (double) (file.getSize() / 1_048_576);
        if(fileSize > 2) {
            throw new BadRequestException("Size file không được vượt quá 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }

    public Image readFile(Integer id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found image");
                });
        return image;
    }
}
