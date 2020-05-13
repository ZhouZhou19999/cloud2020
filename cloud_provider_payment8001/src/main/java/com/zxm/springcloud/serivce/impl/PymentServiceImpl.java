package com.zxm.springcloud.serivce.impl;

import com.zxm.springcloud.dao.PaymentDao;
import com.zxm.springcloud.entities.Payment;
import com.zxm.springcloud.serivce.PymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PymentServiceImpl implements PymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
