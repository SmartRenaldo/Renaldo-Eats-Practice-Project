package com.renaldo.repositories;

import com.renaldo.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>
        , QuerydslPredicateExecutor<Employee> {

    Employee getEmployeeByUsernameAndPassword(String username, String password);

    Employee getEmployeeById(Long id);

    Page<Employee> findAllByNameContains(String nameContains, Pageable pageable);

}
