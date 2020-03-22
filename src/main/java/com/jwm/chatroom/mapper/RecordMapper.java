package com.jwm.chatroom.mapper;

import com.jwm.chatroom.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {

    @Insert("insert into mychat.record(content, created_date, headUrl) values(#{content}, #{created_date}, #{headUrl})")
    void addRecord(Record record);

    @Select("select * from mychat.record order by created_date desc")
    List<Record> showRecords();

    @Select("select * from mychat.record order by created_date desc limit 3")
    List<Record> showRecentRecords();
}
