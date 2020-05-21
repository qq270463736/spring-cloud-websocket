package com.softdev.system.demo.Quartz;


import com.softdev.system.demo.config.SpringUtil;
import com.softdev.system.demo.controller.JobController;
import com.softdev.system.demo.controller.WebSocketServer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Calendar;

public class MyJob implements BaseJob {

    private WebSocketServer webSocketServer = SpringUtil.getBean(WebSocketServer.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String ylbh = context.getJobDetail().getJobDataMap().getString("ylbh");
        try {
            webSocketServer.sendInfo(ylbh,"10");
            System.out.println("任务正在执行，执行时间: " + Calendar.getInstance().getTime()+"   用例编号为:"+ylbh);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}