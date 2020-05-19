package com.zxm.springcloud.serivce.impl;

import com.zxm.springcloud.serivce.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)  //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serail = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serail).build());
        System.out.println("********serail:"+serail);
        return null;
    }
}
