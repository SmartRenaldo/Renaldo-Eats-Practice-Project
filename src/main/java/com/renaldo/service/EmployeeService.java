package com.renaldo.service;

import com.renaldo.common.R;
import com.renaldo.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee getEmployeeByUsernameAndPassword(Employee employee);

    Employee getEmployeeById(Long id);

    Page<Employee> findAllByNameContains(int page, int pageSize, String nameContains);

    Boolean updateEmployeeById(Employee employee);

    String getEmployeeUserNameById(Long currentId);
}
