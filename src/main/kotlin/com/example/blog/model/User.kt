package com.example.blog.model

import java.util.Date;
import javax.persistence.*

@Entity
@Table(name ="t_user")
class User (
    @Id
    @GeneratedValue
    var id: Long,
    var nickname: String,
    var username: String,
    var password: String,
    var email: String,
    var avatar: String,
    var type: Int,
    @Temporal(TemporalType.TIMESTAMP)
    var createTime: Date,
    @Temporal(TemporalType.TIMESTAMP)
    var updateTime: Date,
    @OneToMany(mappedBy = "user")
    var blogs: List<Blog> = ArrayList()
)