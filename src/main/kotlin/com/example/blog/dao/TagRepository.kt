package com.example.blog.dao

import com.example.blog.model.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name : String) : Tag
}