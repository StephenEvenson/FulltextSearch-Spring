package me.stephenj.administration.mapper;

import me.stephenj.administration.model.Equipment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EquipmentMapper {

    @Select("SELECT * FROM `Equipment`")
    List<Equipment> getAll();

    @Insert("INSERT INTO `Equipment` (equipmentNo, name, catagory, lifeTime, purchaseTime, checkTime, departmentNo) VALUES (#{equipmentNo}, #{name}, #{catagory}, #{lifeTime}, #{purchaseTime}, #{checkTime}, #{departmentNo})")
    void insert(Equipment equipment);

    @Update("UPDATE `Equipment` SET name=#{name}, catagory=#{catagory}, lifeTime=#{lifeTime}, purchaseTime=#{purchaseTime}, checkTime=#{checkTime}, departmentNo=#{departmentNo} WHERE equipmentNo=#{equipmentNo}")
    void update(Equipment equipment);

    @Delete("DELETE FROM `Equipment` WHERE equipmentNo=#{equipmentNo}")
    void delete(String equipmentNo);
}
