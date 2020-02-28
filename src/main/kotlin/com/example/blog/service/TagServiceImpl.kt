package com.example.blog.service

import com.example.blog.dao.TagRepository
import com.example.blog.model.Tag
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


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

    @Transactional
    override fun listTag(): List<Tag> {
        return tagRepository.findAll()
    }

    @Transactional
    override fun listTag(ids: String): List<Tag> { //"1,2,3"
        return tagRepository.findAllById(convertToList(ids)!!)
    }

    private fun convertToList(ids: String?): List<Long>? {
        val list: MutableList<Long> = ArrayList()
        if (!ids.isNullOrEmpty()) {
            val idarray = ids.split(",".toRegex()).toTypedArray()
            for (i in idarray.indices) {
                list.add(idarray[i].toLong())
            }
        }
        return list
    }

    override fun listTagTop(size: Int): List<Tag> {
        val order = Sort.Order(Sort.Direction.DESC, "blogs.size")
        val pageable: Pageable = PageRequest.of(0, size, Sort.by(order))
        return tagRepository.findTop(pageable)
    }
}


