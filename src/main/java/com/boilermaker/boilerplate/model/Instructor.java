package com.boilermaker.boilerplate.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "instructor")
public class Instructor extends AuditModel {
@Id
@Column(name="id", nullable=false)
@SequenceGenerator(name="instructor_seq", sequenceName="instructor_id_seq")
@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="instructor_seq")
    private Long id;
    
        private String firstname;
    
        private String lastname;
    
        private Integer age;
    
}

