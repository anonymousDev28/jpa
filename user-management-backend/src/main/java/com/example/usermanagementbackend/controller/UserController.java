package com.example.usermanagementbackend.controller;

import com.example.usermanagementbackend.model.dto.UserDto;
import com.example.usermanagementbackend.model.request.CreateUserRequest;
import com.example.usermanagementbackend.model.request.UpdateAvatarRequest;
import com.example.usermanagementbackend.model.request.UpdatePasswordRequest;
import com.example.usermanagementbackend.model.request.UpdateUserRequest;
import com.example.usermanagementbackend.model.response.FileResponse;
import com.example.usermanagementbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Lấy danh sách user
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok(userDtos);
    }

    // Tìm kiếm user theo tên
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam String name) {
        List<UserDto> userDtos = userService.searchUser(name);
        return ResponseEntity.ok(userDtos);
    }

    // Lấy chi tiết user theo id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    // Tạo user mới
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        UserDto userDto = userService.createUser(request);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    // Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,
                                        @RequestBody UpdateUserRequest request) {
        UserDto userDto = userService.updateUser(id, request);
        return ResponseEntity.ok(userDto);
    }

    // Cập nhật mật khẩu mới
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id,
                                            @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> updatePassword(@PathVariable int id) {
        String password = userService.forgotPassword(id);
        return ResponseEntity.ok(password);
    }

    // Thay đổi avatar của user
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@ModelAttribute("file") MultipartFile file, @PathVariable Integer id) {
        FileResponse fileResponse = userService.updateAvatar(id, file);
        return ResponseEntity.ok(fileResponse);
    }

}

