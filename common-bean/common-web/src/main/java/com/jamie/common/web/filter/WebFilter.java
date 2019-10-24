package com.jamie.common.web.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class WebFilter extends HandlerInterceptorAdapter {
    @Value("${cdn.url}")
    private String cdn;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("cdn", cdn);

        // 直接通过，不进行拦截
        return super.preHandle(request, response, handler);
    }

}
