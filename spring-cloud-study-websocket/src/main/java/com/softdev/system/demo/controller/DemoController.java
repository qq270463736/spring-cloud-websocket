package com.softdev.system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * WebSocketController
 * @author zhengkai.blog.csdn.net
 */
@RestController
public class DemoController {
    @Autowired
    private JobController jodController;
    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @GetMapping("start")
    public ResponseEntity<String> start(){
        try {
            jodController.addJob("com.softdev.system.demo.Quartz.MyJob", "110", "/2 * * * * ?","123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("stop")
    public ResponseEntity<String> stop(){
        try {
            jodController.jobdelete("com.softdev.system.demo.Quartz.MyJob", "110");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("请求成功");
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
