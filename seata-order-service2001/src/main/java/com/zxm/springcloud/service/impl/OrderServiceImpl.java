package com.zxm.springcloud.service.impl;

import com.zxm.springcloud.dao.OrderDao;
import com.zxm.springcloud.domain.Order;
import com.zxm.springcloud.service.AccountService;
import com.zxm.springcloud.service.OrderService;
import com.zxm.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //创建订单
        System.out.println("----->开始新建订单");
        orderDao.create(order);
        //修改库存
        System.out.println("----->订单微服务开始调用库存,做扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("------>订单微服务开始调用库存,做扣减end");
        //减余额
        System.out.println("----->订单微服务开始调用账户,做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("----->订单微服务开始调用账户,做扣减end");
        //改状态
        System.out.println("----->修改订单状态...");
        orderDao.update(order.getUserId(),0);

        System.out.println("----->订单结束");
    }
}
