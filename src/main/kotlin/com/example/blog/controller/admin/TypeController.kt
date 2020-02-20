package com.example.blog.controller.admin

import com.example.blog.model.Type
import com.example.blog.service.TypeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    fun input() : String {
        return "admin/types-input"
    }

    @PostMapping("/types")
    fun post(type : Type) : String {
        val t : Type = typeService.saveType(type)
        if (t == null) {
            //
        } else {
            //
        }

        return "redirect:/admin/types"
    }
}