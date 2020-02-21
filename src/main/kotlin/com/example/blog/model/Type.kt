package com.example.blog.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "t_type")
class Type(
        @Id @GeneratedValue var id: Long? = null,
        @NotBlank(message = "分类名称不能为空")
        var name : String = "",
        @OneToMany(mappedBy = "type")
        var blogs : List<Blog> = ArrayList()
)