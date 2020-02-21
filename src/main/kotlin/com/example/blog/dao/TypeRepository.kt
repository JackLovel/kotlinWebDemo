package com.example.blog.dao


import com.example.blog.model.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TypeRepository : JpaRepository<Type, Long> {
    fun findByName(name : String) : Type
}