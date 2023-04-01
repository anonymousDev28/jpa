package com.techmaster.coursemanagement.repository;


import com.techmaster.coursemanagement.dto.CourseDto;
import com.techmaster.coursemanagement.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

//    @Query("select c " +
//            "from Course c, Category ct " +
//            "where (:name is null or upper(c.name) like upper(concat('%',:name,'%') ) ) " +
//            "and (:type is null or upper(c.type) = upper(:type)) " +
//            "and (:category is null or ct.name = :category)")
//    List<Course> findCourseDemo(
//            @Param("name") String name,
//            @Param("type") String type,
//            @Param("category") String category);


    @Query(nativeQuery = true, name = "findCourseDemo")
    List<CourseDto> findCourseDemo(@Param("name") String name,
                                   @Param("type") String type,
                                   @Param("category") String category);


    @Query(nativeQuery = true, name = "findCourseByAdmin")
    List<CourseDto> findCourseByAdmin(Pageable pageable);
}