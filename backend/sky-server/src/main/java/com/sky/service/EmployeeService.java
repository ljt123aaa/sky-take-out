package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.time.LocalDate;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /*
    * 新增员工
    */
    void addEmployee(EmployeeDTO employeeDTO);

    /*
     * 分页查询
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void enableOrDisableEmployee(Integer status, Long id);

    Employee getEmployeeById(Long id);

    void updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
