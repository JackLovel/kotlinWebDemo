package com.example.blog.service

import com.example.blog.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.blog.model.User
import com.example.blog.util.Md5Util

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun checkUser(username: String, password: String): User {

        var user: User = userRepository.findByUsernameAndPassword(username, Md5Util.code(password).toString())
        return user
    }
}