package com.renaldo.repositories;

import com.renaldo.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>
        , QuerydslPredicateExecutor<Employee> {

    Employee getEmployeeByUsernameAndPassword(String username, String password);

    Employee getEmployeeById(Long id);

    Page<Employee> findAllByNameContains(String nameContains, Pageable pageable);

    @Query("SELECT e.username FROM Employee e WHERE e.id=:id")
    String getUsernameById(@Param("id") Long id);

}
