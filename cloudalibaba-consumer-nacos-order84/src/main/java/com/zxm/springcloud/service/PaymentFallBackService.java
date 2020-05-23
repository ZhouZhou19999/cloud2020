package com.zxm.springcloud.service;

import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(444);
        commonResult.setMessage("OpenFeign的实现兜底方法--");

        return commonResult;
    }
}
