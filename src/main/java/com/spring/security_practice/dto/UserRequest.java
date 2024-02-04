package com.spring.security_practice.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {


    @NotBlank(message = "Please provide FirstName")
    private String firstName;

    @NotBlank(message = "Please provide LastName")
    private String lastName;

    @NotBlank(message = "Please provide UserName")
    private String userName;

    @NotBlank(message = "Please provide Password")
    private String password;

}
