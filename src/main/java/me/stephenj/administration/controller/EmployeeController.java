package me.stephenj.administration.controller;

import me.stephenj.administration.mapper.EmployeeMapper;
import me.stephenj.administration.model.Employee;
import me.stephenj.administration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangruntian
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        System.out.println("get it!");
        List<Employee> employees = employeeMapper.getAll();
        return employees;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(String employeeNo) {
        Employee employee = employeeMapper.getOne(employeeNo);
        return employee;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public void add(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    @RequestMapping(value = "/employee/{employeeNo}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("employeeNo") String employeeNo) {
        employeeMapper.delete(employeeNo);
    }
}
