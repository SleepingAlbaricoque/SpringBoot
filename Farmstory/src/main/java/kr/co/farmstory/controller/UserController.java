package kr.co.farmstory.controller;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("user/terms")
    public String terms(Model model){
        TermsVO terms = service.selectTerms();
        model.addAttribute("terms", terms);
        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("user/register")
    public String register(HttpServletRequest req, UserVO vo){
        vo.setRegip(req.getRemoteAddr());
        int result = service.insertUser(vo);
        return "redirect:login?success=" + result;
    }

    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }
}
