package com.zxm.springcloud.controller;

import com.zxm.springcloud.domain.CommonResult;
import com.zxm.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    //扣减库存
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("扣减库存成功！");
        return commonResult;
    }
}