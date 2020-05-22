package com.zxm.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import com.zxm.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        //return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
        CommonResult commonResult = new CommonResult();
        Payment payment = new Payment();

        payment.setId(2020L);
        payment.setSerial("serial001");
        commonResult.setCode(200);
        commonResult.setMessage("按资源名称限流测试OK");
        commonResult.setData(payment);
        return commonResult;
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        CommonResult commonResult = new CommonResult();
        Payment payment = new Payment();

        payment.setId(2020L);
        payment.setSerial("serial002");
        commonResult.setCode(200);
        commonResult.setMessage("按url限流测试OK");
        commonResult.setData(payment);
        return commonResult;
    }

    public CommonResult handleException(BlockException exception)
    {
        //return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
        CommonResult commonResult = new CommonResult();

        commonResult.setCode(444);
        commonResult.setMessage(exception.getClass().getCanonicalName());
        commonResult.setData("\t 服务不可用");
        return commonResult;
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler()
    {
        CommonResult commonResult = new CommonResult();
        Payment payment = new Payment();

        payment.setId(2020L);
        payment.setSerial("serial003");

        commonResult.setCode(200);
        commonResult.setMessage("按客戶自定义");
        commonResult.setData(payment);
        return commonResult;
    }




}
