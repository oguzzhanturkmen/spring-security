package com.spring.security_practice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book {//--->Student: ManyToOne

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonProperty("bookName")//It will be shown as bookName in JSON format
    private String name;

    @JsonIgnore//This field will be ignored in JSON format
    @ManyToOne
    private Student student;




}
