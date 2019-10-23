package me.stephenj.administration.mapper;

import me.stephenj.administration.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author zhangruntian
 */
public interface UserMapper {
    @Select("SELECT * FROM `Users`")
    List<User> getAll();

    @Select("SELECT password FROM `Users` WHERE user=#{user}")
    String getByUser(String user);

    @Insert("INSERT INTO `Users` (user, password) VALUES (#{user}, #{password})")
    void insert(User user);

    @Update("UPDATE `Users` SET password=#{password} WHERE user=#{user}")
    void update(User user);

    @Delete("DELETE FROM `Users` WHERE user=#{user}")
    void delete(String user);

}
