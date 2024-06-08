package com.example.demo.controller;

import com.example.demo.Response;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 这是一个 Spring MVC 注解，标识这个类是一个控制器，并且它的每个方法返回一个对象，
// Spring 会将这个对象直接转换为 JSON 或 XML 并返回给客户端。
@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    // 将 HTTP GET 请求映射到 getStudentById 方法上。
    // 当客户端发送 GET 请求到 /student/{id} 时，Spring 会调用 getStudentById 方法，
    // 并将 URL 中的 {id} 部分传递给方法参数 id。
    @GetMapping("/student/{id}")
    // @PathVariable Long id：这个注解用来将 URL 路径中的 {id} 部分绑定到方法参数 id 上。
    public Response<StudentDTO> getStudentById(@PathVariable Long id) {
        return Response.newSuccess(service.getStudentById(id));
    }

    // 将 HTTP POST 请求映射到 addNewStudent 方法上。
    // 当客户端发送 POST 请求到 /student 时，Spring 会调用 addNewStudent 方法。
    @PostMapping("/student")
    // 将 HTTP 请求体中的 JSON 数据绑定到方法参数上。
    // 示将请求体中的 JSON 数据绑定到 studentDTO 对象上。
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
        return Response.newSuccess(service.addNewStudent(studentDTO));
    }

    // 将 HTTP Delete 请求映射到 addNewStudent 方法上。
    // 当客户端发送 Delete 请求到 /student 时，Spring 会调用 deleteStudentById 方法。
    @DeleteMapping("/student/{id}")
    // // @PathVariable Long id：这个注解用来将 URL 路径中的 {id} 部分绑定到方法参数 id 上。
    public void deleteStudentById(@PathVariable Long id) {
        service.deleteStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return Response.newSuccess(service.updateStudentById(id, name, email));
    }
}
