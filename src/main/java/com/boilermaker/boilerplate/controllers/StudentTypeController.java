package com.boilermaker.boilerplate.controllers;

import com.boilermaker.boilerplate.exception.ResourceNotFoundException;
import com.boilermaker.boilerplate.model.StudentType;
import com.boilermaker.boilerplate.repository.StudentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class StudentTypeController {
    @Autowired
    private StudentTypeRepository studenttypeRepository;

    @CrossOrigin
    @GetMapping("/studenttype")
    public Page<StudentType> getstudenttypes(Pageable pageable) {
        return studenttypeRepository.findAll(pageable);
    }


    @CrossOrigin
    @GetMapping("/studenttype/{id}")
    public StudentType getById(@PathVariable Long id) {
        StudentType studenttype = studenttypeRepository.getOne(id);
        return studenttype;
    }

    @CrossOrigin
    @PostMapping("/studenttype")
    public StudentType createStudentType(@Valid @RequestBody StudentType type) {
        if(type.getCreatedAt() == null){
            type.setCreatedAt(new Date());
        }
        if(type.getUpdatedAt() == null){
            type.setUpdatedAt(new Date());
        }
        StudentType studenttype = studenttypeRepository.save(type);
        return studenttype;
    }

    @CrossOrigin
    @DeleteMapping("/studenttype/{id}")
    public ResponseEntity<?> deleteStudentType(@PathVariable Long id) {
        return studenttypeRepository.findById(id)
                .map(studentType -> {
                    studenttypeRepository.delete(studentType);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Student Type not found with id " + id));
    }
}
