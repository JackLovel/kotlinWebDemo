package com.example.blog.controller.admin

import com.example.blog.model.Tag
import com.example.blog.model.Type
import com.example.blog.service.TagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.awt.Rectangle
import javax.validation.Valid

@Controller
@RequestMapping("/admin")
class TagController {

    @Autowired
    lateinit var tagService: TagService

    @GetMapping("/tags")
    fun tags(@PageableDefault(size = 3, sort = ["id"], direction = Sort.Direction.DESC)
              pageable : Pageable, model : Model) : String {
        model.addAttribute("page", tagService.listTag(pageable))

        return "admin/tags"
    }

    @GetMapping("/tags/input")
    fun input(model: Model) : String {
        model.addAttribute("tag", Tag())
        return "admin/tags-input"
    }

    @PostMapping("/tags")
    fun post(tag : Tag, attributes: RedirectAttributes) : String {
        var t : Tag = tagService.saveTag(tag)
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败")
        } else {
            attributes.addFlashAttribute("message", "操作成功")
        }

        return "redirect:/admin/tags"
    }

    @GetMapping("/tags/{id}/input")
    fun editInput(@PathVariable id : Long, model: Model) : String {
        model.addAttribute("tag", tagService.getTag(id))
        return "admin/tags-input"
    }

    @PostMapping("/tags/{id}")
    fun editPost(@Valid tag: Tag, result: BindingResult, @PathVariable id: Long, attributes: RedirectAttributes): String {
        val tag1 = tagService.getTagByName(tag.name)
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加分类")
        }
        if (result.hasErrors()) {
            return "admin/tags-input"
        }
        val t : Tag = tagService.updateTag(id, tag)
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败")
        } else {
            attributes.addFlashAttribute("message", "更新成功")
        }
        return "redirect:/admin/tags"
    }


    @GetMapping("/tags/{id}/delete")
    fun deletePost(@PathVariable id : Long, attributes: RedirectAttributes) : String{
        tagService.deleteTag(id)
        attributes.addFlashAttribute("message", "删除成功")
        return "redirect:/admin/tags"
    }
}