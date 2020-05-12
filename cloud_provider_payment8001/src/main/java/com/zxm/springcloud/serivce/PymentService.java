package com.zxm.springcloud.serivce;

import com.zxm.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
