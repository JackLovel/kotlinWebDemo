package com.example.blog.model

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "t_blog")
class Blog(
        @Id @GeneratedValue var id: Long? = null,
        var title: String,
        var content: String,
        var firstPicture: String,
        var flag: String,
        var views: Int,
        var appreciation: Boolean,
        var shareStatement: Boolean,
        var commentabled: Boolean,
        var published: Boolean,
        var recommend: Boolean,

        @Temporal(TemporalType.TIMESTAMP)
        var createTime: Date,

        @Temporal(TemporalType.TIMESTAMP)
        var updateTime: Date,

        @ManyToOne
        var type: Type,

        @ManyToMany(cascade = [CascadeType.PERSIST])
        var tags: List<Tag> = ArrayList(),   // 多博客有多个标签
        @ManyToOne
        var user: User,
        @OneToMany(mappedBy = "blog")
        var comments: List<Comment> = ArrayList()
)