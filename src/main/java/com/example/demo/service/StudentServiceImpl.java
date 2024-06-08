package com.example.demo.service;

import com.example.demo.converter.StudentConverter;
import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service // 这个注解标注在类上，表示这是一个服务层组件, Spring 在扫描组件时将其注册为 Spring Bean
public class StudentServiceImpl implements StudentService {

    // 告诉 Spring 在启动时自动装配（注入）该字段所需的依赖
    // 在这个例子中，private StudentRepository studentRepository 字段会被自动注入一个
    // StudentRepository 类型的实例。
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return StudentConverter.convertStudent(student);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {

        //检测email 唯一性
        List<Student> students = studentRepository.findByEmail(studentDTO.getEmail());
        if (!students.isEmpty()) {
            throw new IllegalStateException("Email: " + studentDTO.getEmail() + "is already occupied.");
        }

        Student student = StudentConverter.convertStudentDTO(studentDTO);
        studentRepository.save(student);
        return student.getId();
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.findById(id).orElseThrow
                (() -> new IllegalStateException("id: " + id + "does not exist"));
        studentRepository.deleteById(id);
    }

    @Override
    // 提交给Spring 管理事务，如果fail, 回滚state
    @Transactional
    public StudentDTO updateStudentById(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow
                (() -> new IllegalStateException("id: " + id + "does not exist"));

        if (StringUtils.hasLength(name) && !student.getName().equals(name)) {
            student.setName(name);
        }

        if (StringUtils.hasLength(email) && !student.getName().equals(email)) {
            student.setEmail(email);
        }

        studentRepository.save(student);
        return StudentConverter.convertStudent(student);
    }
}
