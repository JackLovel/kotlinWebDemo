package com.example.blog.model

import javax.persistence.*

@Entity
@Table(name = "t_type")
class Type(
        @Id @GeneratedValue var id: Long? = null,
        var name : String = "",
        @OneToMany(mappedBy = "type")
        var blogs : List<Blog> = ArrayList()
)