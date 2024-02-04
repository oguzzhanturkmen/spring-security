package com.spring.security_practice.domain;

import lombok.*;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor//sadece final olan(required) fieldları set eden paramli cons oluşturur.
@Entity
public class Student {

/*AllArgsConstructor
    public Student(Long id, String name, String lastName, Integer grade, String email, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
        this.email = email;
        this.createDate = createDate;
    }
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)//setter kullanılamazsın
    private Long id;

    @NotBlank(message = "name can not be blank!")//request anında doğrulama yapar
    @Size(min=2,max = 50,message = "name must be between 2 and 50")//request anında doğrulama yapar
    @Column(nullable = false,length = 50)//db ye ekleme anında doğrulama yapar
    /*final*/ private String name;

    @NotBlank(message = "last name can not be blank!")
    @Size(min=2,max = 50,message = "last name must be between 2 and 50")
    @Column(nullable = false)
    /*final*/ private String lastName;

    @NotNull(message = "Please provide grade!")
    @Column(nullable = false)
    /*final*/ private Integer grade;

    @Column(unique = true)
    @Email(message = "Please provide valid email!")//aaa@bbb.ccc formatında email kabul eder
    //@Pattern()
    private String email;//required

    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate=LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<Book> books=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



/*    public Student(String name, String lastName, Integer grade) {//required
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
    }*/ //@RequiredArgsConstructor

    //getter-setter
}
