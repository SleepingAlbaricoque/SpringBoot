package kr.co.farmstory.controller;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    
    @ResponseBody
    @GetMapping("user/checkUid")
    public Map<String, Integer> checkUid(String uid){
    	int result = service.countUser(uid);
    	Map<String, Integer> map = new HashMap<>();
    	map.put("result", result);
    	return map;
    }
    
    @ResponseBody
    @GetMapping("user/checkNick")
    public Map<String, Integer> checkNick(String nick){
    	int result = service.countUserByNick(nick);
    	Map<String, Integer> map = new HashMap<>();
    	map.put("result", result);
    	return map;
    }
    
    @ResponseBody
    @GetMapping("user/verifyEmail")
    public Map<String, Integer> verifyEmail(String email){
    	int[] result = service.sendEmail(email);
    	Map<String, Integer> map = new HashMap<>();
    	map.put("result", result[0]);
    	map.put("code", result[1]);
    	log.info("result" + result[0]);
    	log.info("code" + result[1]);
    	return map;
    }
}
