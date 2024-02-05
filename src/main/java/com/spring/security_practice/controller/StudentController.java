package com.spring.security_practice.controller;

import com.spring.security_practice.domain.Student;
import com.spring.security_practice.dto.UpdateStudentDTO;
import com.spring.security_practice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Controller
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private Logger logger= LoggerFactory.getLogger(StudentController.class);



    private final StudentService service;

    @GetMapping("/greet")
    @PreAuthorize("hasRole('ADMIN')")
    public String greet(){
        return "Hello Spring Boot:)";
    }

    @GetMapping
    public ResponseEntity<List<Student>> listAllStudents(){

        List<Student> allStudents=service.getAllStudent();

        return ResponseEntity.ok(allStudents);//200
        // return new ResponseEntity<>(allStudents, HttpStatus.OK);//200
    }


    @PostMapping
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){

        service.saveStudent(student);

        Map<String,String> response=new HashMap<>();
        response.put("message","Student is created successfully...");
        response.put("status","active");
        return new ResponseEntity<>(response,HttpStatus.CREATED);//201
    }

    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id ){

        Student found=service.getStudentById(id);

        return new ResponseEntity<>(found,HttpStatus.OK);//200

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable("id") Long id ){

        Student found=service.getStudentById(id);

        return new ResponseEntity<>(found,HttpStatus.OK);//200

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){

        service.deleteStudentById(id);

        return new ResponseEntity<>("Student is deleted successfully...",HttpStatus.OK);//200
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable("id") Long id,
                                                            @Valid @RequestBody UpdateStudentDTO studentDTO){

        service.updateStudentById(id,studentDTO);

        Map<String,String> response=new HashMap<>();
        response.put("message","Student is updated successfully...");
        response.put("status","active");
        return new ResponseEntity<>(response,HttpStatus.CREATED);//201
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllStudentByPage(@RequestParam("page") int page,
                                                             @RequestParam("size") int size,
                                                             @RequestParam("sort") String prop,
                                                             @RequestParam("direction")Sort.Direction direction){

        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));

        Page<Student> studentPage=service.getAllStudentByPage(pageable);

        return new ResponseEntity<>(studentPage,HttpStatus.OK);

    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentByGrade(@PathVariable("grade") Integer grade){

        List<Student> studentList=service.getStudentByGrade(grade);

        return ResponseEntity.ok(studentList);//200
    }

    @GetMapping("/lastname")
    public ResponseEntity<List<Student>> getStudentsByLastName(@RequestParam String lastName){

        List<Student> studentList=service.getAllStudentByLastName(lastName);

        logger.warn("Found records:"+studentList.size());

        return ResponseEntity.ok(studentList);//200
    }



    @GetMapping("/info/{id}")
    public ResponseEntity<UpdateStudentDTO> getStudentInfo(@PathVariable Long id){

        //  UpdateStudentDTO studentDTO=service.getStudentInfo(id);
        UpdateStudentDTO studentDTO=service.getStudentDTO(id);

        logger.info("DTO OBJECT CAME FROM SERVICE" +studentDTO.getName());

        return ResponseEntity.ok(studentDTO);//200

    }



    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){

        logger.info("welcome isteği: "+request.getServletPath());
        logger.info("welcome isteğinin metodu: "+request.getMethod());

        return "welcome";
    }













}
