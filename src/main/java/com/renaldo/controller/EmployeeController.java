package com.renaldo.controller;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.R;
import com.renaldo.pojo.Employee;
import com.renaldo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * The password submitted by the page is encrypted by md5
     * Query the database according to the username and submitted by the page.
     * If no query is found, the login failure result will be returned.
     * Check the employee status, if it is disabled, return the employee disabled result.
     * If the login is successful, the employee id will be stored in the session
     * and the login success result will be returned.
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        Employee employeeByUsernameAndPassword = employeeService.getEmployeeByUsernameAndPassword(employee);

        if (employeeByUsernameAndPassword == null) {
            return R.error("Login failed!");
        }

        if (employeeByUsernameAndPassword.getStatus() == 0) {
            return R.error("This account cannot be used!");
        }

        request.getSession().setAttribute("employee", employeeByUsernameAndPassword.getUsername());

        log.info("Thread id: {}", Thread.currentThread().getId());

        return R.success(employeeByUsernameAndPassword);
    }

    /**
     * set initial password as 123456
     *
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("save employee. employee: {}", employee.toString());
        Employee save = employeeService.save(employee);

        if (save == null) {
            return R.error("save failed!");
        }

        return R.success("Save successfully!");
    }

    /**
     * employee logout
     * clear current employee's id that stores in session
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("Logout successfully!");
    }

    /**
     * paging
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={}, pageSize={}, name={}", page, pageSize, name);
        return R.success(employeeService.findAllByNameContains(page, pageSize, name));
    }

    /**
     * update employee by id
     *
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("Thread id: {}", Thread.currentThread().getId());
        log.info(employee.toString());

        Boolean flag = employeeService.updateEmployeeById(employee);

        if (flag) {
            return R.success("Update successfully!");
        } else {
            return R.error("Data has not changed! Update failed!");
        }
    }

    @GetMapping("/{id}")
    public R<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee != null) {
            return R.success(employee);
        } else {
            return R.error("Did not find this employee!");
        }
    }
}
