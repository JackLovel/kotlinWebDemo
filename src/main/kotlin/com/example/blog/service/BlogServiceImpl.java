package com.example.blog.service;

import com.example.blog.util.MyBeanUtils;
import com.example.blog.dao.BlogRepository;
import com.example.blog.exception.NotFoundException;
import com.example.blog.model.Blog;
import com.example.blog.model.Type;
import com.example.blog.query.BlogQuery;
import com.example.blog.util.MyBeanUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 标签相关的
 */
@Service
public class BlogServiceImpl  implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            // 初始化
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(long id,Blog blog) {
        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            throw  new NotFoundException("该博客不存在");
        }

        BeanUtils.copyProperties(b, blog, MyBeanUtils.getNullPropertyNames(blog));
        return blogRepository.save(b);
    }

    @Override
    public List<Blog> listRecommendBlogTop(int size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, Sort.by(order));
        return blogRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }
}
