package com.spring.security_practice.service;


import com.spring.security_practice.domain.Student;
import com.spring.security_practice.dto.UpdateStudentDTO;
import com.spring.security_practice.exception.ConflictException;
import com.spring.security_practice.exception.StudentNotFoundException;
import com.spring.security_practice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;


    public List<Student> getAllStudent() {

        return repository.findAll();//FROM Student;
    }


    //4-öğrenciyi kaydetme
    public void saveStudent(Student student) {

        //SELECT * FROM student WHERE email=student.getEmail()

        if(repository.existsByEmail(student.getEmail())){
            //bu email daha önce kullanılmış-->hata fırlatalım
            throw new ConflictException("Email already exists!");
        }
        repository.save(student);

    }



    public Student getStudentById(Long id) {
        Student student=repository.findById(id).//select * from student where id=1
                orElseThrow(()->new StudentNotFoundException("Student is not found by id: "+id));
        return student;
    }



    public void deleteStudentById(Long id) {

        //  repository.deleteById(id);//delete from student where id=6;



        Student student=getStudentById(id);
        repository.delete(student);//delete from student where id=5
    }


    public void updateStudentById(Long id, UpdateStudentDTO studentDTO) {

        Student foundStudent=getStudentById(id);


        //DTO daki email:      table:
        //1-ccc@mail.com       tabloda yok V (existsByEmail:false)
        //2-bbb@mail.com       id=4 email:bbb@mail.com  X -->ConflictException (existsByEmail:true)
        //3-aaa@mail.com       id:3 email:aaa@mail.com :kendi maili V (existsByEmail:true)

        //istekte gelen mail daha önce kullanılmış mı
        boolean existEmail=repository.existsByEmail(studentDTO.getEmail());

        if (existEmail && !foundStudent.getEmail().equals(studentDTO.getEmail())){
            throw new ConflictException("Email already exists!");
        }


        foundStudent.setName(studentDTO.getName());
        foundStudent.setLastName(studentDTO.getLastName());
        foundStudent.setEmail(studentDTO.getEmail());

        repository.save(foundStudent);//works like saveOrUpdate in Hibernate

    }

    //Pagination
    public Page<Student> getAllStudentByPage(Pageable pageable){

        return repository.findAll(pageable);

    }

    //14-
    public List<Student> getStudentByGrade(Integer grade) {

        // return repository.findAllByGrade(grade);//SELECT * FROM student WHERE grade=100
        return repository.getAllStudentByGrade(grade);
    }

    //16-ÖDEV
    public List<Student> getAllStudentByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }

    //18-a
    public UpdateStudentDTO getStudentInfo(Long id) {

        Student student=getStudentById(id);

        //  UpdateStudentDTO studentDTO=new UpdateStudentDTO(student.getName(),student.getLastName(),student.getEmail());

        UpdateStudentDTO studentDTO=new UpdateStudentDTO(student);
        return studentDTO;
    }

    //Convert the object coming from table to DTO
    public UpdateStudentDTO getStudentDTO(Long id) {

        UpdateStudentDTO studentDTO=repository.findStudentDTOById(id).
                orElseThrow(()->new StudentNotFoundException("Student is not found by id: "+id));
        return studentDTO;
    }


}
