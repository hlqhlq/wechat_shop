package com.hlq.wxshop.config;

import com.hlq.wxshop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:HLQ
 * @Date:2019/4/10 16:37
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**/**")
                .excludePathPatterns("/wx/**","/pc/index","/pc/login/**");
        super.addInterceptors(registry);
    }
}
