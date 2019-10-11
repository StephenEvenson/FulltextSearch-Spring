package me.stephenj.administration.controller;

import me.stephenj.administration.mapper.SalaryMapper;
import me.stephenj.administration.model.Salary;
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
public class SalaryController {

    @Autowired
    private SalaryMapper salaryMapper;

    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public List<Salary> getSalaries() {
        List<Salary> salaries = salaryMapper.getAll();
        return salaries;
    }

    @RequestMapping(value = "/salary", method = RequestMethod.POST)
    public void add(Salary salary) {
        salaryMapper.insert(salary);
    }

    @RequestMapping(value = "/salary", method = RequestMethod.PUT)
    public void update(Salary salary) {
        salaryMapper.update(salary);
    }

    @RequestMapping(value = "/salary/{salarySerialNo}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("salarySerialNo") String salarySerialNo) {
        salaryMapper.delete(salarySerialNo);
    }
}
