package com.example.blog.controller.admin

import com.example.blog.model.User
import com.example.blog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/admin")
class LoginController {

    @Autowired
    lateinit var userService : UserService

    @GetMapping
    fun loginPage() : String {
        return "admin/login"
    }

    @PostMapping("login")
    fun login(@RequestParam username : String,
              @RequestParam password : String,
              session : HttpSession,
              attributes: RedirectAttributes) : String {
        var user : User = userService.checkUser(username = username, password = password)
        if (user != null) {
            user.password = null.toString()
            session.setAttribute("user", user) // 添加用户会话
            return "admin/index"
        } else {
            // 用户登录不成功时的错误信息
            attributes.addFlashAttribute("message", "用户名和密码错误")
            return "redirect:/admin"
        }
    }

    @GetMapping("logout")
    fun logout(session : HttpSession) : String {
        session.removeAttribute("user") // 移除 用户 会话
        return "redirect:/admin"
    }
}