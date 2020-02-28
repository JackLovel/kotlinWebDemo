package com.example.blog.dao


import com.example.blog.model.Type
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TypeRepository : JpaRepository<Type, Long> {
    fun findByName(name : String) : Type

    @Query("select t from Type t")
    fun findTop(pageable: Pageable) : List<Type>
}