package kr.co.ch08.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ch08.service.User1Service;
import kr.co.ch08.vo.User1VO;

@Controller
public class User1Controller {
	
	@Autowired
	private User1Service service;

	@GetMapping("/user1/login")
	public String login() {
		return "/user1/login";
	}
	
	@GetMapping("/user1/loginSuccess")
	public String loginSuccess() {
		return "/user1/loginSuccess";
	}
	
	@PostMapping("/user1/login")
	public String login(String uid, String pass, HttpSession sess) {
		User1VO user = service.selectUser1(uid, pass);
		
		if(user != null) {
			// 회원 o
			sess.setAttribute("sessUser", user);
			return "redirect:/user1/loginSuccess";
		}else {
			return "redirect:/user1/login?success=100";
		}
	}
	
	@GetMapping("/user1/logout")
	public String logout(HttpSession sess) {
		sess.invalidate();
		return "redirect:/user1/login?success=101";
	}
}
