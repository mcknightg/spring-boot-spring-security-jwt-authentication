package com.boilermaker.boilerplate.controllers;

import com.boilermaker.boilerplate.exception.ResourceNotFoundException;
import com.boilermaker.boilerplate.model.Student;
import com.boilermaker.boilerplate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @CrossOrigin
    @GetMapping("/student")
    public Page<Student> getstudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    @CrossOrigin
    @GetMapping("/student/{id}")
    public Student getById(@PathVariable Long id) {
        Student student = studentRepository.getOne(id);
        return student;
    }

    @CrossOrigin
    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student) {
        Student students = studentRepository.save(student);
        return students;
    }

    @CrossOrigin
    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }
}

