package com.example.blog.service

import com.example.blog.model.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TagService {
    fun saveTag(tag : Tag) : Tag // 保存
    fun getTag(id : Long) : Tag // 查找
    fun getTagByName(name : String) : Tag
    fun listTag(page : Pageable) : Page<Tag> // 分页
    fun updateTag(id : Long, tag : Tag) : Tag // 更新
    fun deleteTag(id : Long)
}