package com.example.blog.service

import com.example.blog.model.Blog
import com.example.blog.query.BlogQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BlogService {
    fun getBlog(id : Long) : Blog
    fun listBlog(pageable: Pageable, blog: BlogQuery) : Page<Blog>
    fun saveBlog(blog: Blog) : Blog
    fun updateBlog(id : Long, blog: Blog) : Blog
    fun deleteBlog(id : Long)
}