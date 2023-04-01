package com.techmaster.coursemanagement.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCourse {
    private String name;
    private String description;
    private String type;
    private List<Integer> category;
    private Integer price;
    private String thumbnail;
    private Integer userId;
}
