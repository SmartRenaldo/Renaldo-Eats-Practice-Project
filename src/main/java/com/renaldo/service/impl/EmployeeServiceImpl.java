package com.renaldo.service.impl;

import com.renaldo.pojo.Employee;
import com.renaldo.repositories.EmployeeRepository;
import com.renaldo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByUsernameAndPassword(Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        return employeeRepository.getEmployeeByUsernameAndPassword(employee.getUsername(), password);
    }

    @Override
    public Employee save(Employee employee) {
        // avoid null pointer exception
        String password = employee.getPassword() == null ? "123456" : employee.getPassword();
        employee.setPassword(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));

        if (employee.getStatus() == null) {
            employee.setStatus(1);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public Page<Employee> findAllByNameContains(int page, int pageSize, String nameContains) {
        if (nameContains == null) {
            return employeeRepository.findAll(PageRequest.of(page - 1, pageSize));
        } else {
            return employeeRepository.findAllByNameContains(nameContains, PageRequest.of(page - 1, pageSize));
        }
    }

    @Override
    @Transactional
    public Boolean updateEmployeeById(Employee employee) {
        //check if there is update
        boolean flag = false;

        Employee employeeById = employeeRepository.getEmployeeById(employee.getId());

        if (StringUtils.hasText(employee.getName())) {
            employeeById.setName(employee.getName());
            flag = true;
        }

        if (StringUtils.hasText(employee.getPassword())) {
            employeeById.setPassword(DigestUtils
                    .md5DigestAsHex(employee.getPassword()
                            .getBytes(StandardCharsets.UTF_8)));
            flag = true;
        }

        if (StringUtils.hasText(employee.getPhone())) {
            employeeById.setPhone(employee.getPhone());
            flag = true;
        }

        if (StringUtils.hasText(employee.getGender())) {
            employeeById.setGender(employee.getGender());
            flag = true;
        }

        if (employee.getStatus() != null) {
            employeeById.setStatus(employee.getStatus());
            flag = true;
        }

        if (StringUtils.hasText(employee.getLastModifiedBy())) {
            employeeById.setLastModifiedBy(employee.getLastModifiedBy());
            flag = true;
        }

        return flag;
    }
}
