package me.stephenj.administration.service;

import me.stephenj.administration.mapper.DepartmentMapper;
import me.stephenj.administration.mapper.EmployeeMapper;
import me.stephenj.administration.mapper.PositionMapper;
import me.stephenj.administration.model.Employee;
import me.stephenj.administration.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    PositionMapper positionMapper;

    public Employee fillEmployee(Employee employee) {
        String departmentNo = departmentMapper.getNoByName(employee.getDepartmentName());
        System.out.println("departmentNo: " + departmentNo);
        employee.setDepartmentNo(departmentNo);
        List<Position> positions = positionMapper.getAllByDepartmentNo(departmentNo);
        System.out.println("positions: " + positions.toString());
        for (Position position : positions ) {
            System.out.println("positionNo: " + position.getPositionNo());
            if (position.getName().equals(employee.getPositionName())) {
                employee.setPositionNo(position.getPositionNo());
            }
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        employee = fillEmployee(employee);
        employeeMapper.insert(employee);
    }

    public void updateEmployee(Employee employee) {
        employee = fillEmployee(employee);
        employeeMapper.update(employee);
    }

    public List<Employee> showEmployee(int page) {
        int rows = 10;
        List<Employee> result = new ArrayList<Employee>();
        List<Employee> employees = employeeMapper.getAll();
        if (employees.size() > (page-1)*rows) {
            int index = 0;
            for (Employee emploee : employees) {
                if (index <  page * 10 && index >= (page-1) * 10) {
                    result.add(emploee);
                }
                System.out.println(index);
                index += 1;
                if (index >= page * 10) {
                    break;
                }
            }
        }
        return result;
    }
}
