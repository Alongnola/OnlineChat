package com.jwm.chatroom.service;

import com.jwm.chatroom.mapper.RecordMapper;
import com.jwm.chatroom.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    public List<Record> showRecords() {
        return recordMapper.showRecords();
    }

    public List<Record> showRecentRecords() {
        return recordMapper.showRecentRecords();
    }

    public List<Record> searchRecords(String pattern) {
        return null;
    }

    public void addRecord(Record record){
        recordMapper.addRecord(record);
    }
}
