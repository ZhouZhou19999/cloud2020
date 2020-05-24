package com.zxm.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.zxm.springcloud.dao"})
public class MybatisConfig {
}
