package com.tian.blog.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tian
 * @date 2020/7/8
 */

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/delete/*","perms[user:delete]");
//        filterMap.put("/user/update/*","perms[user:update]");
//        filterMap.put("/user/get/*","perms[user:get]");
//        filterMap.put("/user/listAll","perms[user:all]");
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/update/**","authc");
        filterMap.put("/user/delete/**","authc");

        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/noAuth");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
