package me.stephenj.administration.mapper;

import me.stephenj.administration.model.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartmentMapper {
    @Select("SELECT * FROM `Department`")
    List<Department> getAll();

    @Select("SELECT * FROM `Department` WHERE departmentNo=#{departmentNo}")
    Department getOne(String departmentNo);

    @Select("SELECT departmentNo FROM `Department` WHERE name=#{name}")
    String getNoByName(String departmentName);

    @Insert("INSERT INTO `Department` (departmentNo, name, employeeQuantity) VALUES (#{departmentNo}, #{name}, #{employeeQuantity})")
    void insert(Department department);

    @Update("UPDATE `Department` SET name=#{name}, employeeQuantity=#{employeeQuantity} WHERE departmentNo=#{departmentNo}")
    void update(Department department);

    @Delete("DELETE FROM `Department` WHERE departmentNo=#{departmentNo}")
    void delete(String departmentNo);
}
