package me.stephenj.administration.mapper;

import me.stephenj.administration.model.Position;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PositionMapper {
   @Select("SELECT * FROM `Position`")
   List<Position> getAll();

   @Select("SELECT * FROM `Position` WHERE positionNo = #{positionNo}")
   Position getOne(String positionNo);

   @Select("SELECT * FROM `Position` p JOIN `PositionInDepartment` pi ON p.positionNo=pi.positionNo WHERE pi.departmentNo=#{departmentNo}")
   List<Position> getAllByDepartmentNo(String departmentNo);

   @Insert("INSERT INTO `Position` (positionNo, name) VALUES (#{positionNo}, #{name})")
   void insert(Position position);

   @Update("UPDATE `Position` SET positionNo=#{positionNo}, name=#{name} WHERE positionNo=#{positionNo}")
   void update(Position position);

   @Delete("DELETE FROM `Position` WHERE positionNo=#{positionNo}")
   void delete(String positionNo);
}
