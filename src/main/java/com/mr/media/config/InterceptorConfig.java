package com.mr.media.config;

import com.mr.media.interceptor.TokenInterceptor;
import com.mr.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    UserService userService = new UserService();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(userService))
                .addPathPatterns("/user/**",
                        "/admin/**", "/agent/**", "/actor/**")
                .excludePathPatterns("/user/login",
                        "/WeChat/**");

        super.addInterceptors(registry);
    }
}
