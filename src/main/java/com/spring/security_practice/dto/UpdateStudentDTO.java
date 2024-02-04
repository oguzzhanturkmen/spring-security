package com.spring.security_practice.dto;

import com.spring.security_practice.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDTO {

    @NotBlank(message = "name can not be blank!")//Request is checked for validation as soon as it comes
    @Size(min=2,max = 50,message = "name must be between 2 and 50")//Request is checked for validation as soon as it comes
    private String name;

    @NotBlank(message = "last name can not be blank!")
    @Size(min=2,max = 50,message = "last name must be between 2 and 50")
    private String lastName;


    @Email(message = "Please provide valid email!")//It only accepts email format
    private String email;//required

    public UpdateStudentDTO(Student student){
        this.name=student.getName();
        this.lastName=student.getLastName();
        this.email=student.getEmail();

    }


}
