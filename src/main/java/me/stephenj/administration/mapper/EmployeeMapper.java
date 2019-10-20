package me.stephenj.administration.mapper;

import me.stephenj.administration.enums.EmployeeGenderEnum;
import me.stephenj.administration.model.Employee;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangruntian
 */
public interface EmployeeMapper extends Serializable {

    @Select("SELECT employeeNo, gender, e.name name, age, telephoneNo, p.positionNo, p.name positionName, d.departmentNo, d.name departmentName FROM `Employee` e LEFT JOIN `Position` p ON e.positionNo=p.positionNo LEFT JOIN `Department` d ON e.departmentNo=d.departmentNo;")
    @Results({
            @Result(property = "gender", column = "gender", javaType = EmployeeGenderEnum.class)
    })
    List<Employee> getAll();

    @Select("SELECT * FROM `Employee` WHERE employeeNo = #{employeeNo}")
    @Results({
            @Result(property = "gender", column = "gender", javaType = EmployeeGenderEnum.class)
    })
    Employee getOne(String employeeNo);

    @Select("SELECT employeeNo, gender, e.name name, age, telephoneNo, p.positionNo, p.name positionName, d.departmentNo, d.name departmentName FROM `Employee` e LEFT JOIN `Position` p ON e.positionNo=p.positionNo LEFT JOIN `Department` d ON e.departmentNo=d.departmentNo WHERE e.name=#{employeeName};")
    @Results({
            @Result(property = "gender", column = "gender", javaType = EmployeeGenderEnum.class)
    })
    List<Employee> getByName(String employeeName);

    @Insert("INSERT INTO `Employee` (employeeNo, gender, name, age, telephoneNo, positionNo, departmentNo) VALUES (#{employeeNo}, #{gender}, #{name}, #{age}, #{telephoneNo}, #{positionNo}, #{departmentNo})")
    void insert(Employee employee);

    @Update("UPDATE `Employee` SET gender=#{gender}, name=#{name}, age=#{age}, telephoneNo=#{telephoneNo}, positionNo=#{positionNo}, departmentNo=#{departmentNo} WHERE employeeNo = #{employeeNo}")
    void update(Employee employee);

    @Delete("DELETE FROM `Employee` WHERE employeeNo=#{employeeNo}")
    void delete(String employeeNo);
}
