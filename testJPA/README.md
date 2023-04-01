Câu 1: 
Thuộc tính name trong annotation @Entity dùng để chỉ định tên của đối tượng entity trong CSDL, còn thuộc tính name trong @Table được sử dụng để chỉ định tên của bảng trong CSDL. Nếu không chỉ định tên cho @Entity thì Hibernate sẽ sử dụng tên của class làm tên entity, và nếu không chỉ định tên cho @Table thì Hibernate sẽ sử dụng tên của entity làm tên bảng.

Câu 2: 
Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, ta có thể bổ sung lệnh "logging.level.org.hibernate.SQL=DEBUG" vào file application.properties.

Câu 3: 
Tham số name trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính, tham số unique chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu, tham số nullable = false buộc trường không được null.

Câu 4: 
Annotation @PrePersist được sử dụng để bắt sự kiện trước khi một đối tượng Entity lưu xuống CSDL, và annotation @PreUpdate được sử dụng để bắt sự kiện trước khi một đối tượng Entity cập nhật xuống CSDL.

Câu 5: 
JpaRepository kế thừa từ interface PagingAndSortingRepository.

Câu 6: 
Khai báo interface repository thao tác với một Entity tên là Post:


    public interface PostRepository extends JpaRepository<Post, Long> {
    }


Câu 7: 
Khi sử dụng @Id để đánh dấu một cột là Identity thì không cần sử dụng @Column(unique=true), vì @Id đã bao gồm tính năng unique.

Câu 8: 
Các method trong interface EmployeeRepository:
```java
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);
    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
    List<Employee> findByFirstNameIgnoreCase(String firstName);
    }
```
    

Câu 9: 
@NamedQuery là một annotation để định nghĩa một câu lệnh truy vấn có tên được đặt trước trong annotation, dùng để tối ưu hóa hiệu suất khi thực thi truy vấn.

Ví dụ:
```java
    @Entity
    @NamedQuery(name = "findEmployeesByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
    public class Employee {
    
    }
```

@Query là một annotation để định nghĩa một câu lệnh truy vấn inline bằng cách sử dụng JPQL hoặc native SQL.

Ví dụ:

```java
    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
 
    @Query(value = "SELECT * FROM employee WHERE email_address = ?1", nativeQuery = true)
    Employee findByEmailAddress(String emailAddress);

    }
```
   


Câu 10:

ví dụ sử dụng sorting và paging khi query đối tượng Employee:
```java
    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName, Pageable pageable);
    }

```
    

Trong đó, Pageable được sử dụng để định nghĩa các thông số phân trang như số trang, số phần tử trên mỗi trang, vị trí bắt đầu, thứ tự sắp xếp và trường sắp xếp.


        Pageable pageable = PageRequest.of(0, 10, Sort.by("firstName").ascending());
        List<Employee> employees = employeeRepository.findByLastNameOrderByFirstNameAsc("Smith", pageable);

   


Câu 11:
class category:
```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="category")
    public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
    @PreRemove
    private void removeFromProducts() {
        for (Product product : products) {
            product.setCategory(null);
        }
    }
    }
```
    

class Product:

```java
    @Table(name = "product")
    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    }
```
    

class tag:
```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="tag")
    public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Product> products = new ArrayList<>();
    }
```
   
Câu 12:

    @Query("SELECT new com.techmaster.jpatest.dto.UserDTO(u.id, u.name, u.email) FROM User u")
    List<UserDTO> findAllUserDTOJPQL();

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> findAllUserDTONative();

    List<UserProjection> findAllUserProjection();

Câu 13:
```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="post")
    public class Post {
    @Id
    // cách 2 sử dụng generator
    //    @GeneratedValue(generator = "uuid2")
    //    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    //    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;
    private String title;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
    }
```
