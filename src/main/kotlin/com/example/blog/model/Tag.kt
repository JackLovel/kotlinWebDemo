package com.example.blog.model

import javax.persistence.*

@Entity
@Table(name = "t_tag")
class Tag(
        @Id
        @GeneratedValue
        var id: Long,
        var name : String,
        @ManyToMany(mappedBy = "tags")
        var blogs : List<Blog> = ArrayList()
)