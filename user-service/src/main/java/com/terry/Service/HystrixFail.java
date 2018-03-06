package com.terry.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/***
 * *
 * 名称：     HystrixFail.
 * 作者：     Terry Tan
 * 创建时间：  on 2018/2/23.
 * 说明：     
 * *
 ***/

@Component
public class HystrixFail implements TokenAPI {

    private final Logger log = LoggerFactory.getLogger(HystrixFail.class);

    @Override
    public String getToken(String uid) {
        log.error("远程调用失败，服务降级，{}", uid);
        return null;
    }

}
