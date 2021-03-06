package com.example.blog.controller

import com.example.blog.service.BlogService
import com.example.blog.service.TagService
import com.example.blog.service.TypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class IndexController {

    @Autowired
    lateinit var blogService: BlogService
    @Autowired
    lateinit var tagService: TagService
    @Autowired
    lateinit var typeService: TypeService


    @GetMapping("/")
    fun index(@PageableDefault(size = 3, sort = ["updateTime"], direction = Sort.Direction.DESC)
              pageable: Pageable, model: Model) : String {
        model.addAttribute("page", blogService.listBlog(pageable))
        model.addAttribute("types", typeService.listTypeTop(6))
        model.addAttribute("tags", tagService.listTagTop(10))
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8))
        return "index";
    }


    @PostMapping("/search")
    fun search(@PageableDefault(size = 8, sort = ["updateTime"], direction = Sort.Direction.DESC)
              pageable: Pageable,  @RequestParam query : String, model: Model) : String {
        model.addAttribute("page", blogService.listBlog("%$query%", pageable))
        model.addAttribute("query", query)
        return "search"
    }

    @GetMapping("/blog/{id}")
    fun blog(@PathVariable id: Long, model: Model) : String {
//        model.addAttribute("blog", "")
        model.addAttribute("blog", blogService.getAndConvert(id))
        return "blog";
    }
}





























