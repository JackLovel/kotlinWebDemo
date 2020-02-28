package com.example.blog.dao

import com.example.blog.model.Tag
import com.example.blog.model.Type
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name : String) : Tag

    @Query("select t from Tag t")
    fun findTop(pageable: Pageable) : List<Tag>
}