package com.example.blog.model

import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name ="t_comment")
class Comment (
        @Id @GeneratedValue var id: Long? = null,
        var nickname : String = "",
        var email : String = "",
        var content : String = "",
        var avatar : String = "",
        @Temporal(TemporalType.TIMESTAMP)
        var createTime: Date = Date(),

        @ManyToOne
        var blog : Blog,
        @OneToMany(mappedBy = "parentComment")
        var replyComments : List<Comment> = ArrayList(),
        @ManyToOne
        var parentComment : Comment)