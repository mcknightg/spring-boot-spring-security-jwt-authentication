package com.boilermaker.boilerplate.controllers;
import com.boilermaker.boilerplate.exception.ResourceNotFoundException;
import com.boilermaker.boilerplate.model.Instructor;
import com.boilermaker.boilerplate.model.Student;
import com.boilermaker.boilerplate.repository.InstructorRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @CrossOrigin
    @GetMapping("/instructor")
    public Page<Instructor> getinstructors(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }
    @CrossOrigin
    @GetMapping("/instructor/{id}")
    public Instructor getById(@PathVariable Long id) {
        Instructor instructor = instructorRepository.getOne(id);
        return instructor;
    }
    @CrossOrigin
    @PostMapping("/instructor")
    public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {
        if(instructor.getCreatedAt() == null){
            instructor.setCreatedAt(new Date());
        }
        if(instructor.getUpdatedAt() == null){
            instructor.setUpdatedAt(new Date());
        }
        Instructor instructors = instructorRepository.save(instructor);
        return instructors;
    }

    @CrossOrigin
    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructorRepository.delete(instructor);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + id));
    }
}

