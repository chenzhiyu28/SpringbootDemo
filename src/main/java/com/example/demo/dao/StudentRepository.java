package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// 这个JpaRepository 有很多功能，且按照 它的命名方式 自定义method，也可以被spring自动实现
// 这里repository 功能是 把 代码转化成 sql， 访问 database
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    // ①按照命名规则，可以被SpringBoot 自动识别，无需自己写sql
    // find支持的关键字: And, Between, In, StartingWith
    List<Student> findByEmail(String email);

    List<Student> findByAgeBetween(int min, int max);

    List<Student> findByNameStartingWith(String prefix);

    // ② 通过jqpl(一种java内的sql语言)查询
    // jqpl中，这里的Student 是java class，不是student table
    // @Param中的email 用来 和 jpql中的:email 对应
    @Query("select stu from Student stu where email = :email")
    List<Student> findByEmail2(@Param("email") String email);

    // case2: 使用多个field，则需要student 拥有对应的 constructor (还是用原生SQL把)
    // 所以这个方法目前不可用,必须注释掉不然spring汇报错
    /*
    @Query("Select new com.example.demo.dao.Student(name, email) from Student stu where email = :email")
    List<Student> findByNameAndEmail(@Param("email")String email);
     */

    // ③ 通过原生SQL查询
    @Query(value = "Select * from student where email = :email", nativeQuery = true)
    List<Student> findByEmail3(@Param("email") String email);
}
