package kr.co.sboard;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class SBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBoardApplication.class, args);
	}

	@GetMapping(value= {"", "index"})
	public String index(Principal principal) {
		if(principal != null) {
			return "redirect:/list";
		}else {
			return "redirect:/user/login";
		}
	}
}
