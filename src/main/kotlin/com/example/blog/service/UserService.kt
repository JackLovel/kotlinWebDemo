package com.example.blog.service

import com.example.blog.model.User

interface UserService {
    fun checkUser(username : String, password : String) : User
}