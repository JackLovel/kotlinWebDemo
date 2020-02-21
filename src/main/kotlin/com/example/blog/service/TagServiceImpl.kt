package com.example.blog.service

import com.example.blog.dao.TagRepository
import com.example.blog.model.Tag
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.Exception

@Service
class TagServiceImpl : TagService {
    
    @Autowired
    lateinit var tagRepository: TagRepository

    // 保存
    @Transactional
    override fun saveTag(tag: Tag): Tag {
        return tagRepository.save(tag)
    }

    @Transactional
    override fun getTag(id: Long): Tag {
        return tagRepository.findById(id).get()
    }

    // 分页
    @Transactional
    override fun listTag(page: Pageable): Page<Tag> {
        return tagRepository.findAll(page)
    }

    @Transactional
    override fun updateTag(id: Long, tag: Tag): Tag {
        var t : Tag = tagRepository.findById(id).get()

        if (t == null) {
            throw Exception("不存在该类型")
        }

        BeanUtils.copyProperties(tag, t)
        return tagRepository.save(t)
    }

    @Transactional
    override fun deleteTag(id: Long) {
        tagRepository.deleteById(id)
    }

    override fun getTagByName(name: String): Tag {
        return tagRepository.findByName(name)
    }
}