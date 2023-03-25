package com.example.usermanagementbackend.repository;

import com.example.usermanagementbackend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Integer> {
}