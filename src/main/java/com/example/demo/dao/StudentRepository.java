package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 按照命名规则，可以被SpringBoot 自动识别，无需自己写sql
    List<Student> findByEmail(String email);
}
