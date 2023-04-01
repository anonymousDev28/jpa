package com.techmaster.jpatest.repository;

import com.techmaster.jpatest.dto.UserDTO;
import com.techmaster.jpatest.model.User;
import com.techmaster.jpatest.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.techmaster.jpatest.dto.UserDTO(u.id, u.name, u.email) FROM User u")
    List<UserDTO> findAllUserDTOJPQL();

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> findAllUserDTONative();

    List<UserProjection> findAllUserProjection();



}