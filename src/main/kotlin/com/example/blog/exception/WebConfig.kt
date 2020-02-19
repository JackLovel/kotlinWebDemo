package com.example.blog.exception

import com.example.blog.interceptor.LoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebConfig : WebMvcConfigurerAdapter() {

    // 拦截器
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin") // 不拦截 /admin 路由
                .excludePathPatterns("/admin/login") // 不拦截 /admin/login 路由

        super.addInterceptors(registry)
    }
}