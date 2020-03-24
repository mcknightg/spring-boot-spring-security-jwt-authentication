package com.boilermaker.boilerplate.repository;

import com.boilermaker.boilerplate.model.StudentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTypeRepository extends JpaRepository<StudentType, Long> {
}