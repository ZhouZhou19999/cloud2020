package com.zxm.springcloud.controller;


import com.zxm.springcloud.entities.CommonResult;
import com.zxm.springcloud.entities.Payment;
import com.zxm.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OderController {
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){

        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //使用entity方法访问
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            CommonResult commonResult = new CommonResult();
            commonResult.setCode(404);
            commonResult.setMessage("访问错误");
            return commonResult;
        }
    }

    @GetMapping("/consumer/payment/createEntity")
    public CommonResult create2(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            CommonResult commonResult = new CommonResult();
            commonResult.setCode(404);
            commonResult.setMessage("存储错误");
            return commonResult;
        }
    }
    //自己写的ribbon算法，如果需要使用将loadbalancer关闭
//    @GetMapping("/consumer/payment/lb")
//    public String getPaymentLB(){
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//
//        if (instances == null || instances.size() <= 0){
//            return null;
//        }else {
//            ServiceInstance serviceInstance = loadBalancer.instances(instances);
//            URI uri = serviceInstance.getUri();
//            return restTemplate.getForObject(uri+"/payment/lb",String.class);
//        }
//    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }



}
