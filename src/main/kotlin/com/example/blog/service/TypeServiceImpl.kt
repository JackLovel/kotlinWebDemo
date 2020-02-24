package com.example.blog.service

import com.example.blog.dao.TypeRepository
import com.example.blog.model.Type
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TypeServiceImpl : TypeService {
    @Autowired
    lateinit var typeRepository : TypeRepository

    @Transactional
    override fun saveType(type: Type): Type {
        return typeRepository.save(type)
    }

    @Transactional
    override fun getType(id: Long): Type {
        return typeRepository.findById(id).get()
    }

    @Transactional // 事务
    override fun listType(pageable: Pageable): Page<Type> {
        return typeRepository.findAll(pageable)
    }

    @Transactional
    override fun updateType(id: Long, type: Type): Type {
        var t : Type = typeRepository.findById(id).get()

        if (t == null) {
            throw Exception("不存在该类型")
        }

        BeanUtils.copyProperties(type, t)
        return typeRepository.save(t)
    }

    @Transactional
    override fun deleteType(id: Long) {
        typeRepository.deleteById(id)
    }

    override fun getTypeByName(name: String): Type {
        return typeRepository.findByName(name)
    }

    override fun listType(): List<Type> {
        return typeRepository.findAll()
    }
}