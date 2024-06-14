package com.example.demo.dao;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class StudentSpecifications {

    public Specification<Student> buildSpecification(StudentSearchCriteria criteria) {

        // root：Specification<T> 中的 T 的root path(这里就是student的 root path)
        // query：表示创建 Criteria 查询对象。它允许你自定义查询，例如设置查询结果的排序和分组。
        // cb = criteriaBuilder用于构建查询条件。 cb.method()都返回criteria对象
        return (root, query, cb) -> {
            // Predicate 是 JPA Criteria API 中的一个接口，表示查询条件
            // 这里用LIST保存所有条件
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getName() != null) {
                predicates.add(cb.equal(root.get("name"), criteria.getName()));
            }
            if (criteria.getMinAge() > 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("age"), criteria.getMinAge()));
            }
            if (criteria.getMaxAge() > 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("age"), criteria.getMaxAge()));
            }
            if (criteria.getEmail() != null) {
                predicates.add(cb.equal(root.get("email"), criteria.getEmail()));
            }
            if (criteria.getNamePrefix() != null) {
                predicates.add(cb.like(root.get("name"), criteria.getNamePrefix() + "%"));
            }
            if (criteria.getEmailSuffix() != null) {
                predicates.add(cb.like(root.get("email"), "%" + criteria.getEmailSuffix()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
