package com.terry;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/***
 * *
 * 名称：     MyFilter.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/12/5.
 * 说明：     
 * *
 ***/

@Component
public class TokenFilter extends ZuulFilter {

    private final Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    TokenAPI tokenAPI;

    @Override
    public String filterType() {
        // 路由之前调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String uri = request.getRequestURI();
        log.info("请求路径：{}", uri);
        //对用户相关接口进行过滤
        return uri.startsWith("/user-service/user/");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        if (token == null | !tokenAPI.verify(token)) {
            //token验证失败，抛出异常拦截请求
            log.error("token验证失败，抛出异常拦截请求");
            ctx.setResponseStatusCode(499);
            throw new TokenException("token error");
        }
        return null;
    }

}
