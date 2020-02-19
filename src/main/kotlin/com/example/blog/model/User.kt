package com.example.blog.model

import java.util.Date;
import javax.persistence.*

@Entity
@Table(name = "t_user")
class User(
        // 添加 一些 默认的值
        @Id @GeneratedValue var id: Long? = null,
        var nickname: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var avatar: String  = "",
        var type: Int  = 0,
        @Temporal(TemporalType.TIMESTAMP)
        var createTime: Date = Date(),
        @Temporal(TemporalType.TIMESTAMP)
        var updateTime: Date = Date(),
        @OneToMany(mappedBy = "user")
        var blogs: List<Blog> = ArrayList()
) {
        override fun toString(): String {
                return "User(id=$id, nickname='$nickname', username='$username', password='$password', email='$email', avatar='$avatar', type=$type, createTime=$createTime, updateTime=$updateTime, blogs=$blogs)"
        }
}