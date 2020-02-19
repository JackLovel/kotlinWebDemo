package com.example.blog.handler

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerExceptionHandler {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler
    fun exceptionHander(request: HttpServletRequest, e:Exception): ModelAndView {
        logger.error("Request URL : {}, Exception : {}", request.requestURL, e)

        if (AnnotationUtils.findAnnotation(e.javaClass, ResponseStatus::class.java) != null) {
            throw e
        }

        val mv = ModelAndView();
        mv.addObject("url", request.requestURL);
        mv.addObject("exception", e);
        mv.addObject("error/error");

        return mv
    }

}