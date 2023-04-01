package com.techmaster.jpatest.service;

import com.techmaster.jpatest.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserDTO> findUsers();
}
