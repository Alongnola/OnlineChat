package com.jwm.chatroom.mapper;

import com.jwm.chatroom.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from mychat.user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select count(*) from mychat.user where username = #{username}")
    Integer findByUsername(String username);

    @Insert("insert into mychat.user(username, password, created_date, headUrl) values(#{username}, #{password}, " +
            "#{created_date}, #{headUrl})")
    void save(User user);

}
