package com.example.blog.model

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "t_blog")
class Blog(
        @Id @GeneratedValue var id: Long? = null,
        var title: String = "",
        var content: String = "",
        var firstPicture: String = "",
        var flag: String = "",
        var views: Int = 0,
        var appreciation: Boolean = false,
        var shareStatement: Boolean = false,
        var commentabled: Boolean = false,
        var published: Boolean = false,
        var recommend: Boolean = false,

        @Temporal(TemporalType.TIMESTAMP)
        var createTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        var updateTime: Date = Date(),

        @ManyToOne
        var type: Type,

        @ManyToMany(cascade = [CascadeType.PERSIST])
        var tags: List<Tag> = ArrayList(),   // 多博客有多个标签
        @ManyToOne
        var user: User,
        @OneToMany(mappedBy = "blog")
        var comments: List<Comment> = ArrayList()
)