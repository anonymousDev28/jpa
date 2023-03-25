package com.example.usermanagementbackend;

import com.example.usermanagementbackend.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManagementBackendApplicationTests {


    @Autowired
    private FileService fileService;

    @Test
    void getFileExtension_test() {
        System.out.println(fileService.getFileExtension("abc.png"));
        System.out.println(fileService.getFileExtension("image.123.jpg"));
    }

}
