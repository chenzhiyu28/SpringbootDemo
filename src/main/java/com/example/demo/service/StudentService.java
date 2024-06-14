package com.example.demo.service;

import com.example.demo.dao.StudentSearchCriteria;
import com.example.demo.dao.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO getStudentById(Long id);

    Long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(Long id);

    StudentDTO updateStudentById(Long id, String name, String email);

    List<StudentDTO> findStudents(StudentSearchCriteria criteria);
}

