package com.example.demo.dao;

import jakarta.persistence.*;


@Entity //通过 @Entity 注解，你可以告诉 JPA 框架将这个类映射到数据库中的一个表。
@Table(name = "student") //用于指定实体类映射到数据库中的哪个表。


public class Student {

    @Id // 用于指定一个属性是实体类的主键
    @Column(name = "id")

    // 用于指定主键的生成策略。GenerationType.IDENTITY 指定了主键由数据库自动生成。
    // 适用于数据库支持自动递增主键。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }
}
