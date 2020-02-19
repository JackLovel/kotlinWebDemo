package com.example.blog.model

import javax.persistence.*

@Entity
@Table(name = "t_tag")
data class Tag(
        @Id @GeneratedValue var id: Long? = null,
        var name : String = "",
        @ManyToMany(mappedBy = "tags")
        var blogs : List<Blog> = ArrayList()
)