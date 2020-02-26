package com.wu.blog.controller;

import com.wu.blog.dto.PageDTO;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public  String index(HttpServletRequest request,
                         Model model,
                         @RequestParam(name = "page",defaultValue ="1") Integer page,
                         @RequestParam(name = "size",defaultValue = "5") Integer size,
                         @RequestParam(name = "search",required = false) String search){
        PageDTO pageDTOs=questionService.list(search,page,size);
        model.addAttribute("pageDTOs",pageDTOs);
        model.addAttribute("search",search);
        return "index";
    }
}
