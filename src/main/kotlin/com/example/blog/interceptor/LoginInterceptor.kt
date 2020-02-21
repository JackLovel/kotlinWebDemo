package com.example.blog.interceptor

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// 登录拦截器
class LoginInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.session.getAttribute("user") == null) {
            response.sendRedirect("/admin")
            return false
        }

        return true
    }
}