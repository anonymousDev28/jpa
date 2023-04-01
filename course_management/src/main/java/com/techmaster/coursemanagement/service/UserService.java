package com.techmaster.coursemanagement.service;

import com.techmaster.coursemanagement.dto.CourseDto;
import com.techmaster.coursemanagement.exception.BadRequest;
import com.techmaster.coursemanagement.model.Course;
import com.techmaster.coursemanagement.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final CourseRepository courseRepository;

    public List<CourseDto> getAllCourse(String name, String type, String category) {
        System.out.println(category);
        return courseRepository.findCourseDemo(name,type,category);
    }

    public Course getCourse(Integer id) {
        Optional<Course> courseOptional =  courseRepository.findById(id);
        if (courseOptional.isPresent()){
            return courseOptional.get();
        } else {
            throw new BadRequest("khong tim thay id");
        }
    }
}
