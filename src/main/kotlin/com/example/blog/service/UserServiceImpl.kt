package com.example.blog.service

import com.example.blog.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.blog.model.User

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository : UserRepository

    override fun checkUser(username: String, password: String): User {

        return userRepository.findByUsernameAndPassword(username, password)
    }
}