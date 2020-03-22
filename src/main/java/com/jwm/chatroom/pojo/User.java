package com.jwm.chatroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String username;

    private String password;

    private LocalDateTime created_date;

    private String headUrl;

}
