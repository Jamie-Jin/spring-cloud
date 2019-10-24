package com.jamie.web.login.filter;

import com.jamie.common.web.filter.WebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 使用自定义拦截器WebFilter
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    @Bean
    public WebFilter webFilter(){
        WebFilter webFilter = new WebFilter();
        return webFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webFilter()).addPathPatterns("/**").excludePathPatterns("");
        super.addInterceptors(registry);
    }

}
