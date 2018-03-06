package com.terry;

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
public class VerifyFail implements TokenAPI {

    private final Logger log = LoggerFactory.getLogger(VerifyFail.class);

    @Override
    public boolean verify(String token) {
        log.error("远程调用失败，服务降级，{}", token);
        return false;
    }

}
