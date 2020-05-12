package com.zxm.springcloud.controller;


import com.zxm.springcloud.serivce.PymentService;
import org.springframework.web.bind.annotation.*;

import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PymentService paymentService;
    private CommonResult commonResult;
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        commonResult = new CommonResult();
        int i = paymentService.create(payment);
        if (i>0){
            commonResult.setCode(200);
            commonResult.setMessage("存储成功");
            commonResult.setData(i);

        }else {
            commonResult.setCode(404);
            commonResult.setMessage("存储失败");
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
            commonResult.setMessage("访问成功");
            commonResult.setData(payment);
        }else {
            commonResult.setCode(400);
            commonResult.setMessage("访问失败");
            commonResult.setData(payment);
        }
        return commonResult;
    }
}
