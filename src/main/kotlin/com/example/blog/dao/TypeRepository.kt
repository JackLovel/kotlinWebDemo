package com.example.blog.dao


import com.example.blog.model.Type
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRepository : JpaRepository<Type, Long> {
    fun findByName(name : String) : Type
}