package com.techmaster.jpatest;

import com.github.javafaker.Faker;
import com.techmaster.jpatest.dto.UserDTO;
import com.techmaster.jpatest.model.User;
import com.techmaster.jpatest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class JpAtestApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testJPQL() {
        ModelMapper modelMapper = new ModelMapper();
//        userRepository
//                .findAllUserDTONative()
//                .stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList()).forEach(System.out::println);
        assert(userRepository.findAllUserProjection().size() == 10);
//        userRepository.findAllUserProjection();
    }
    @Test
    void addUser(){
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            userRepository.save(
                    User.builder()
                            .name(faker.name().fullName())
                            .email(faker.internet().emailAddress())
                            .password(faker.phoneNumber().cellPhone()).build()
            );
        }
        assert(userRepository.findAll().size() == 10);
    }
}
