package com.jwm.chatroom.controller;

import com.jwm.chatroom.pojo.Record;
import com.jwm.chatroom.pojo.User;
import com.jwm.chatroom.service.RecordService;
import com.jwm.chatroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;
    private Random random = new Random();

    // 跳转至注册页面
    @RequestMapping("/tryRegistration")
    public ModelAndView registration() {
        return new ModelAndView("registration");
    }


    @PostMapping("/register")
    public ModelAndView register(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=gb2312");
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (username.contains("#")) {
            out.print("<script language=\"javascript\">alert('用户名中不能包含特殊字符！');</script>");
            return new ModelAndView("registration");
        }

        int count = userService.findByUsername(username);
        if (count == 0) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            String head = String.format("http://q6nsl510z.bkt.clouddn.com/%d.jpg", random.nextInt(22) + 1);
            user.setHeadUrl(head);
            user.setCreated_date(UDateToLocalDateTime());
            userService.save(user);
            //注册成功，重定向登录页面
            out.print("<script language=\"javascript\">alert('注册成功！');</script>");

            return new ModelAndView("index");
        } else {
            //失败重定向注册页面
            out.print("<script language=\"javascript\">alert('该用户名已被占用！');</script>");
            return new ModelAndView("registration");
        }
    }


    // 跳转至登陆页面
    @RequestMapping("/tryLogin")
    public ModelAndView login() {
        return new ModelAndView("index");
    }


    @PostMapping("/login")
    public ModelAndView login(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              Map<String, Object> map, HttpServletResponse response) {

        User user = userService.findByUsernameAndPassword(username, password);
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=gb2312");
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (user != null) {
                List<Record> records = recordService.showRecentRecords();
                List<Record> recentRecords = new ArrayList<>();
                LocalDateTime time = LocalDateTime.now();
                for (int i = records.size() - 1; i >= 0; i--) {
                    Record record = records.get(i);
                    recentRecords.add(record);
                    time = record.getCreated_date();
                }
                map.put("username", username);
                map.put("headUrl", user.getHeadUrl());
                map.put("recentRecords", recentRecords);
                map.put("time", time);
                // System.out.println(map);
                return new ModelAndView("chat", map);
            } else {
                out.print("<script language=\"javascript\">alert('用户名或密码错误，请重试！');</script>");
                return new ModelAndView("index");
            }
        }
    }

    public LocalDateTime UDateToLocalDateTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }
}
