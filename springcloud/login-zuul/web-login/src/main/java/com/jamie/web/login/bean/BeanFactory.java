package com.jamie.web.login.bean;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class BeanFactory {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        // TODO 设置Freemarker模板路径（这个好像是多余的，在application.yml已设置）
        freeMarkerConfigurer.setTemplateLoaderPaths("classpath:/templates/");

        Properties properties = new Properties();
        properties.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.put("number_format", "0.######");
        properties.put("defaultEncoding", "UTF-8");
        properties.put("url_escaping_charset", "UTF-8");
        properties.put("locale", "zh_CN");
        properties.put("template_update_delay", "0");
        freeMarkerConfigurer.setFreemarkerSettings(properties);
        freeMarkerConfigurer.setPreferFileSystemAccess(false);

        Map<String, Object> param = new HashMap<>();
        param.put("block", new BlockDirective());
        param.put("override", new OverrideDirective());
        param.put("extends", new ExtendsDirective());
        freeMarkerConfigurer.setFreemarkerVariables(param);

        return freeMarkerConfigurer;
    }

}
