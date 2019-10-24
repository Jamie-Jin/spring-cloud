package com.jamie.service.login.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 获取跳转登录页之前的访问路径
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // 重定向路径
        String targetUrl = "/user/login-page";

        // 当缓存不为空时（说明用户已经访问过了，不管有没有通过验证）
        if (savedRequest != null){
            targetUrl = savedRequest.getRedirectUrl();
        }

        // 重定向到登录前访问的路径
        response.sendRedirect(targetUrl);
    }
}
