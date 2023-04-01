package com.techmaster.jpatest.service;

import com.techmaster.jpatest.dto.UserDTO;
import com.techmaster.jpatest.model.User;
import com.techmaster.jpatest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> findUsers() {
        List<User> users = userRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return users.stream().map(user -> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
    }
}
