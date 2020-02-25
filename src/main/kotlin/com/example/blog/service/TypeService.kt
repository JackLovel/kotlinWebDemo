package com.example.blog.service

import com.example.blog.model.Type
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TypeService {
    fun saveType(type : Type) : Type // 保存
    fun getType(id: Long?) : Type // 查找
    fun getTypeByName(name : String) : Type
    fun listType(pageable: Pageable) : Page<Type> // 分页
    fun listType() : List<Type>
    fun updateType(id : Long, type : Type) : Type // 更新
    fun deleteType(id : Long)
}