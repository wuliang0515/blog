package com.wu.blog.controller;

import com.wu.blog.domain.User;
import com.wu.blog.dto.PageDTO;
import com.wu.blog.mapper.UserMapper;
import com.wu.blog.service.NotificationService;
import com.wu.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public  String profile(@PathVariable(name = "action") String action,
                           @RequestParam(value = "page",defaultValue ="1") Integer page,
                           @RequestParam(value = "size",defaultValue = "5") Integer size,
                           Model model,
                           HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的发布");
            PageDTO pageDTOs = questionService.list(user.getId(), page, size);
            model.addAttribute("pageDTOs",pageDTOs);
        }else if("replies".equals(action)){
            PageDTO pageDTOs=notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pageDTOs",pageDTOs);
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
