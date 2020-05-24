package com.zxm.springcloud.controller;

import com.zxm.springcloud.domain.CommonResult;
import com.zxm.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("扣减账户余额成功！");
        return commonResult;
    }
}
