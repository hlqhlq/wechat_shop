package com.hlq.wxshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author:HLQ
 * @Date:2019/4/10 15:12
 */

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法安全设置
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public  PasswordEncoder passwordEncoder(){
//        //使用BCrypt 加密
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        return daoAuthenticationProvider;
//    }
//
//    /**
//     * 自定义配置
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/css/**","/js/**","fonts/**").permitAll()
//                .and()
//                .formLogin().successForwardUrl("/pc/login/success")
//                .loginPage("/index")
//                .loginProcessingUrl("/login")
//                .failureUrl("/pc/login-error")
//                .and().exceptionHandling().accessDeniedPage("/403");
//    }
//
//    /**
//     * 认证信息管理
//     * @param auth
//     * @throws Exception
//     */
//    @Autowired
//    public void configGlobal(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(userDetailsService);
//        auth.authenticationProvider(authenticationProvider());
//    }
//}
