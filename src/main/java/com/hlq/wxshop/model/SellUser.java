package com.hlq.wxshop.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * 卖家实体类
 * @Author:HLQ
 * @Date:2019/4/10 12:57
 */
@Entity
@Data
public class SellUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    /**
     * 是否删除标识
     */
    private Integer isDel=0;

    private String createTime;

    private String updateTime;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public void setEncodePassword(String password) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodePasswd = encoder.encode(password);
//        this.password = encodePasswd;
//    }
}
