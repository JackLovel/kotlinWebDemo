package com.example.blog.dao


import com.example.blog.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsernameAndPassword(username : String, password : String) : User
}