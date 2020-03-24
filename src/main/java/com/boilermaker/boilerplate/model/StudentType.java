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
@Table(name = "studenttype")
public class StudentType extends AuditModel {
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name="student_seq", sequenceName="student_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="student_seq")
    private Long id;
    private String name;
}
