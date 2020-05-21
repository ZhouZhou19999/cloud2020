package com.zxm.springcloud.controller;


import com.netflix.discovery.DiscoveryClient;
import com.zxm.springcloud.serivce.PymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Resource
    private PymentService paymentService;
    private CommonResult commonResult;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        commonResult = new CommonResult();
        int i = paymentService.create(payment);
        if (i>0){
            commonResult.setCode(200);
            commonResult.setMessage("存储成功,端口为:"+serverPort);
            commonResult.setData(i);

        }else {
            commonResult.setCode(404);
            commonResult.setMessage("存储失败,端口为:"+serverPort);
            commonResult.setData(null);
        }
        return commonResult;
    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        commonResult = new CommonResult();
        if (payment!=null){  //说明有数据，能查询成功

            commonResult.setCode(200);
            commonResult.setMessage("访问成功,端口为:"+serverPort);
            commonResult.setData(payment);
        }else {
            commonResult.setCode(400);
            commonResult.setMessage("访问失败,端口为:"+serverPort);
            commonResult.setData(null);
        }
        return commonResult;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to zxm，O(∩_∩)O哈哈~";
    }
}
