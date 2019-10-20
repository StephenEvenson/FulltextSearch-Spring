package me.stephenj.administration.controller;

import me.stephenj.administration.mapper.EmployeeMapper;
import me.stephenj.administration.model.Employee;
import me.stephenj.administration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Employee> getEmployees(@RequestParam(defaultValue = "") String employeeName) {
        System.out.println("get it!");
        List<Employee> employees = null;
        if (employeeName != null && employeeName.length() > 0) {
            employees = employeeMapper.getByName(employeeName);
        } else {
            employees = employeeMapper.getAll();
        }
        return employees;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(String employeeNo) {
        return employeeMapper.getOne(employeeNo);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public void add(Employee employee) {
        employeeService.addEmployee(employee);
        System.out.println("add a employee record");
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
