package com.techmaster.coursemanagement.dto;

import lombok.*;

import java.sql.Array;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageDto {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalItems;
    private List<CourseDto> data;
}
