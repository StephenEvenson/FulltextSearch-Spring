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
    public List<Employee> getEmployees(@RequestParam(defaultValue = "") String employeeName, @RequestParam(defaultValue = "1") String currentPage) {
        System.out.println("get it!");
        List<Employee> employees = null;
        int page = Integer.parseInt(currentPage);
        if (employeeName != null && employeeName.length() > 0) {
            employees = employeeMapper.getByName(employeeName);
        } else {
            employees = employeeService.showEmployee(page);
        }
        return employees;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(String employeeNo) {
        return employeeMapper.getOne(employeeNo);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public void add(Employee employee) {
        System.out.println("get a post request");
        employeeService.addEmployee(employee);
        System.out.println("add a employee record");
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public void update(Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/employee/{employeeNo}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("employeeNo") String employeeNo) {
        employeeMapper.delete(employeeNo);
    }
}
