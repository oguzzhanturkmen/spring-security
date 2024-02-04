package com.spring.security_practice.repository;
import com.spring.security_practice.domain.Student;
import com.spring.security_practice.dto.UpdateStudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{


    boolean existsByEmail(String email);//!! Implemented by Spring Data JPA


    List<Student> findAllByGrade(Integer grade);//SELECT * FROM student WHERE grade=100


    //JPQL
    @Query("FROM Student WHERE grade=:pGrade")
    List<Student> getAllStudentByGrade(@Param("pGrade") Integer grade);


    //SQL
    @Query(value = "SELECT * FROM student WHERE grade=:pGrade",nativeQuery = true)
    List<Student> getAllStudentByGradeSQL(@Param("pGrade") Integer grade);

    //16-a
    List<Student> findAllByLastName(String lastName);//SELECT * FROM student WHERE lastName=:lastName

    //With JPQL an object coming from table is converted to DTO
    @Query("SELECT new com.spring.security_practice.dto.UpdateStudentDTO(s) FROM Student s WHERE id=:pid")
    Optional<UpdateStudentDTO> findStudentDTOById(@Param("pid") Long id);
}
