package com.example.demo;

import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.StudentSearchCriteria;
import com.example.demo.dao.dto.StudentDTO;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

@SpringBootTest
public class BootDemoApplicationTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServiceImpl service;

    @Test
    void test() {
        List<Student> students = studentRepository.findByEmail3("ABC@123.com");
        System.out.println(students);

        // Specification 允许在运行时动态构建查询条件，
        // 可以根据不同的业务需求动态组合查询条件，而不需要提前定义所有可能的查询方法。
        StudentSearchCriteria criteria = StudentSearchCriteria.builder()
                .name("ERICC")
                .minAge(10)
                .maxAge(30)
                .build();
        List<StudentDTO> result = service.findStudents(criteria);
        System.out.println(result);
    }
}