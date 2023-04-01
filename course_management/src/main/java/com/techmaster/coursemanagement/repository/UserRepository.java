package com.techmaster.coursemanagement.repository;

import com.techmaster.coursemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}