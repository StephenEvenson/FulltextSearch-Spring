package me.stephenj.administration.mapper;

import me.stephenj.administration.model.Salary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SalaryMapper {
    @Select("SELECT * FROM `Salary`")
    List<Salary> getAll();

    @Insert("INSERT INTO `Salary` (salarySerialNo, year, amount, employeeNo) VALUES (#{salarySerialNo}, #{year}, #{amount}, #{employeeNo})")
    void insert(Salary salary);

    @Update("UPDATE `Salary` SET year=#{year}, amount=#{amount}, employeeNo=#{employeeNo} WHERE salarySerialNo=#{salarySerialNo}")
    void update(Salary salary);

    @Delete("DELETE FROM `Salary` WHERE salarySerialNo=#{salarySerialNo}")
    void delete(String salarySerialNo);
}
