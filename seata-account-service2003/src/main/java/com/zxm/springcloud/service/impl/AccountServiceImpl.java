package com.zxm.springcloud.service.impl;

import com.zxm.springcloud.dao.AccountDao;
import com.zxm.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    //private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        //LOGGER.info("------->account-service中扣减余额开始");
        accountDao.decrease(userId,money);
        //LOGGER.info("------->account-service中扣减余额开始");
    }
}
