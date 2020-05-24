package com.zxm.springcloud.controller;

import com.zxm.springcloud.domain.CommonResult;
import com.zxm.springcloud.domain.Order;
import com.zxm.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("订单创建成功。。。。");
        return commonResult;
    }


}
