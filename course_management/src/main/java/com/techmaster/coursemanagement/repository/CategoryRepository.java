package com.techmaster.coursemanagement.repository;


import com.techmaster.coursemanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}