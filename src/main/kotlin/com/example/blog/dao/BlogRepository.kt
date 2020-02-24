package com.example.blog.dao

import com.example.blog.model.Blog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BlogRepository : JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}