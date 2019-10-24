package com.jamie.service.login.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        RequestCache requestCache = new HttpSessionRequestCache();
        // 获取跳转登录页之前的访问路径
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String targetUrl = "/user/login-success";
        if (savedRequest != null){
            targetUrl = savedRequest.getRedirectUrl();
        }

        // 重定向到登录前访问的路径
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
