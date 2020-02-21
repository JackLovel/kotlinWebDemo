package com.example.blog.controller.admin

import com.example.blog.model.Type
import com.example.blog.service.TypeService
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
import javax.validation.Valid

@Controller
@RequestMapping("/admin")
class TypeController {

    @Autowired
    lateinit var typeService: TypeService

    @GetMapping("/types")
    fun types(@PageableDefault(size = 3, sort = ["id"], direction = Sort.Direction.DESC)
              pageable : Pageable, model : Model) : String {
        model.addAttribute("page", typeService.listType(pageable))

        return "admin/types"
    }

    @GetMapping("/types/input")
    fun input(model: Model) : String {
        model.addAttribute("type", Type())
        return "admin/types-input"
    }

    @PostMapping("/types")
    fun post(@Valid type : Type, result: BindingResult, attributes: RedirectAttributes) : String {
//        val type1 : Type = typeService.getTypeByName(type.name) // 检查数据库里是否有相同的值
//        if (type1 != null) {
//            result.rejectValue("name", "nameError", "不能重复添加分类")
//        }

        // 后端不为空检验
        if(result.hasErrors()) {
            return "admin/types-input"
        }
        val t : Type = typeService.saveType(type)
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败")
        } else {
            attributes.addFlashAttribute("message", "操作成功")
        }

        return "redirect:/admin/types"
    }



    @PostMapping("/types/{id}")
    fun editPost(@Valid type: Type,
                 result: BindingResult,
                 @PathVariable id: Long,
                 attributes: RedirectAttributes): String {
        return ""
    }


    @GetMapping("/types/{id}/delete")
    fun deletePost(@PathVariable id : Long, attributes: RedirectAttributes) : String{
        typeService.deleteType(id)
        attributes.addFlashAttribute("message", "删除成功")
        return "redirect:/admin/types"
    }
}