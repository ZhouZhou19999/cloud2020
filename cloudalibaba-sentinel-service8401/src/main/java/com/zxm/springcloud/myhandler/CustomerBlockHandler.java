package com.zxm.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zxm.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException2(BlockException exception)
    {
        //return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
        CommonResult commonResult = new CommonResult();

        commonResult.setCode(444);
        commonResult.setMessage("用户自定义，global handler------1");
        return commonResult;
    }
}
