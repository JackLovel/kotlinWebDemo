package com.example.blog.controller.admin

import com.example.blog.model.Blog
import com.example.blog.query.BlogQuery
import com.example.blog.service.BlogService
import com.example.blog.service.TagService
import com.example.blog.service.TypeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
class BlogController {

    val INPUT: String = "admin/blogs-input"
    val LIST: String = "admin/blogs"
    val REDIRECT_LIST: String = "redirect:/admin/blogs"

    @Autowired
    lateinit var blogService: BlogService

    @Autowired
    lateinit var typeService: TypeService

    @Autowired
    lateinit var tagService: TagService

    @GetMapping("/blogs")
    fun blogs(@PageableDefault(size = 2, sort = ["updateTime"], direction = Sort.Direction.DESC) pageable: Pageable,
              model: Model, blog: BlogQuery): String {
        model.addAttribute("types", typeService.listType())
        model.addAttribute("page", blogService.listBlog(pageable, blog))
        return LIST
    }

    @PostMapping("/blogs/search")
    fun search(@PageableDefault(size = 2, sort = ["updateTime"], direction = Sort.Direction.DESC) pageable: Pageable,
               blog: BlogQuery, model: Model): String {
        model.addAttribute("page", blogService.listBlog(pageable, blog))
        return "admin/blogs :: blogList"
    }

    @GetMapping("/blogs/input")
    fun input(model: Model): String {
        model.addAttribute("types", typeService.listType())
        model.addAttribute("tags", tagService.listTag())
        model.addAttribute("blog", Blog())
        return INPUT
    }
}

