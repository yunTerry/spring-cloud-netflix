package com.terry.Service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 * *
 * 名称：     UserAPI.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/7/7.
 * 说明：     
 * *
 ***/

@FeignClient(value = "token-service", fallback = HystrixFail.class)
public interface TokenAPI {

    @GetMapping("/token/generate/{uid}")
    String getToken(@PathVariable("uid") String uid);

}
