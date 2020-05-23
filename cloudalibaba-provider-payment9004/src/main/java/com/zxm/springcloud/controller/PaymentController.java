package com.zxm.springcloud.controller;

import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static{
        Payment p1 = new Payment();
        p1.setId(1l);
        p1.setSerial("28a8c1e3bc2742d8848569891fb42181");
        Payment p2 = new Payment();
        p2.setId(2l);
        p2.setSerial("bba8c1e3bc2742d8848569891ac32182");
        Payment p3 = new Payment();
        p3.setId(3l);
        p3.setSerial("6ua8c1e3bc2742d8848569891xt92183");

        hashMap.put(1L,p1);
        hashMap.put(2L,p2);
        hashMap.put(3L,p3);
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult();
        result.setCode(200);
        result.setMessage("from mysql,serverPort:  "+serverPort);
        result.setData(payment);
        return result;
    }



}