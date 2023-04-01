package com.techmaster.coursemanagement.model;

import com.techmaster.coursemanagement.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "listCourse", // tên kết quả ở bước 1
                classes = @ConstructorResult(
                        targetClass = CourseDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "type", type = String.class),
                                @ColumnResult(name = "thumbnail", type = String.class),
                                @ColumnResult(name = "price", type = Integer.class),
                                @ColumnResult(name = "rating", type = Double.class),
                                @ColumnResult(name = "user", type = String.class)
                        }
                )

        )
})
@NamedNativeQuery(
        name = "findCourseDemo", // Đặt tên cho câu lệnh query sử dụng trong repo
        resultSetMapping = "listCourse", // Đặt tên cho kết quả trả về -> sử dụng ở step 2
        query = """
                select c.*,
                JSON_OBJECT("id", u.id, "name", u.name, "email", u.email, "phone", u.phone, "avatar", u.avatar) as user
                from course c
                left join course_categories cc\s
                on c.id = cc.course_id
                left join category ct
                on cc.categories_id = ct.id
                left join user u
                on c.user_id = u.id
                where (:name is null or upper(c.name) like upper(concat('%',:name,'%') )\s)
                and (:type is null or c.type = :type)
                and (:category is null or ct.name = :category)
                group by c.id
                """) // Định nghĩa câu lệnh native query
@NamedNativeQuery(
        name = "findCourseByAdmin", // Đặt tên cho câu lệnh query sử dụng trong repo
        resultSetMapping = "listCourse", // Đặt tên cho kết quả trả về -> sử dụng ở step 2
        query = """
                select c.*,
                JSON_OBJECT("id", u.id, "name", u.name, "email", u.email, "phone", u.phone, "avatar", u.avatar) as user
                from course c
                left join user u
                on c.user_id = u.id
                """) // Định nghĩa câu lệnh native query
@Table(name = "course")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Course implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "price")
    private Integer price;

    @Column(name = "rating")
    private Double rating;

//    @JsonProperty("user_info") //tra ve voi ten duoc dat
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @JsonIgnore //khong tra ve
    @ManyToMany
    @JoinTable(name = "course_categories",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();
}