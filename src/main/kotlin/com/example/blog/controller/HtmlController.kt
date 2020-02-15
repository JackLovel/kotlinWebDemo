package com.example.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {
    @GetMapping("/")
    fun blog(model: Model) : String {
        model["title"] = "首页"
        model["content"] = "你好世界！"
        return "blog"
    }
}