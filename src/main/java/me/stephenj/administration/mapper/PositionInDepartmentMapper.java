package me.stephenj.administration.mapper;

import me.stephenj.administration.model.PositionInDepartment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PositionInDepartmentMapper {
    @Select("SELECT * FROM `PositionInDepartment`")
    List<PositionInDepartment> getAll();

    @Insert("INSERT INTO `PositionInDepartment` (positionNo, departmentNo) VALUES (#{positionNo}, #{departmentNo})")
    void insert(PositionInDepartment positionInDepartment);

    @Delete("DELETE FROM `PositionInDepartment` WHERE positionNo=#{positionNo} AND departmentNo=#{departmentNo}")
    void delete(PositionInDepartment positionInDepartment);
}
