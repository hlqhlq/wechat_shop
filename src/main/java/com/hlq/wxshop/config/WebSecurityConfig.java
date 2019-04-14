package com.hlq.wxshop.config;

import com.hlq.wxshop.common.constant.ComConstant;
import com.hlq.wxshop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
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
                .excludePathPatterns("/wx/**","/pc/index","/pc/login/**","/upload/**");
        super.addInterceptors(registry);
    }

    /**
     * 图片绝对地址与虚拟地址映射
     * 对文件的路径进行配置,创建一个虚拟路径/files/**，即只要在<img src="/files/picName.jpg" />便可以直接引用图片
     * 这是图片的物理路径  "file:/+本地图片的地址"
     * @param registry
     * @return
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ComConstant.FILEADDR)
                .addResourceLocations("file:///"+System.getProperty("user.dir").replace("\\\\","/")+"/");
        super.addResourceHandlers(registry);
    }
}
