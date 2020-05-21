package com.zxm.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {

        return "------testB";
    }
    @GetMapping("/testD")
    public String testD()
    {
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        int age = 10/0;
        return "------testD";
    }
    @GetMapping("/testE")
    public String testE()
    {
        int age = 10/0;
        return "------testE 测试异常数";
    }




}
