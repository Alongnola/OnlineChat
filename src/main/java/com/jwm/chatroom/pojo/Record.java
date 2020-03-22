package com.jwm.chatroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 记录消息的实体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;

    private String content;

    private LocalDateTime created_date;

    private String headUrl;

}

