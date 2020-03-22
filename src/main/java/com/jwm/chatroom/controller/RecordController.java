package com.jwm.chatroom.controller;

import com.jwm.chatroom.pojo.Record;
import com.jwm.chatroom.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping("/chatRecords")
    public ModelAndView showRecords(Map<String, List<Record>> map, HttpServletResponse response) {
        List<Record> records = recordService.showRecords();
        map.put("records", records);
        //System.out.println(records);

        return new ModelAndView("record", map);
    }

}
