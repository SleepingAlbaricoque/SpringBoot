package kr.co.ch07.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.ch07.repository.User1Repo;
import kr.co.ch07.vo.User1VO;
import kr.co.ch07.vo.User2VO;

@Controller
public class MainController {
	
	@Autowired
	private User1Repo repo;

	@GetMapping(value = {"/", "/index"})
	public String index() {
		
		return "/index";
	}
	
	@GetMapping("/query1")
	public String query1() {
		User1VO user= repo.findUser1VOByUid("a102");
		System.out.println("query1 결과: " + user);
		return "redirect:/";
	}
	@GetMapping("/query2")
	public String query2() {
		List<User1VO> users = repo.findUser1VOByName("김춘추"); // list 결과 참조값아닌 값이 그대로 출력 thanks to Lombok
		System.out.println("query2 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query3")
	public String query3() {
		List<User1VO> users = repo.findUser1VOByNameNot("김춘추");
		System.out.println("query3 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query4")
	public String query4() {
		List<User1VO> users = repo.findUser1VOByUidAndName("a102", "김춘추");
		System.out.println("query4 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query5")
	public String query5() {
		List<User1VO> users = repo.findUser1VOByUidOrName("a103", "김춘추");
		System.out.println("query5 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query6")
	public String query6() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThan(12);
		System.out.println("query6 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query7")
	public String query7() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThanEqual(23);
		System.out.println("query7 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query8")
	public String query8() {
		List<User1VO> users = repo.findUser1VOByAgeLessThan(23);
		System.out.println("query8 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query9")
	public String query9() {
		List<User1VO> users = repo.findUser1VOByAgeLessThanEqual(23);
		System.out.println("query9 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query10")
	public String query10() {
		List<User1VO> users = repo.findUser1VOByNameLike("김");
		System.out.println("query10 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query11")
	public String query11() {
		List<User1VO> users = repo.findUser1VOByNameContains("김");
		System.out.println("query11 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query12")
	public String query12() {
		List<User1VO> users = repo.findUser1VOByNameStartsWith("김");
		System.out.println("query12 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query13")
	public String query13() {
		List<User1VO> users = repo.findUser1VOByNameEndsWith("무");
		System.out.println("query13 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query14")
	public String query14() {
		List<User1VO> users = repo.findUser1VOByOrderByName();
		System.out.println("query14 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query15")
	public String query15() {
		List<User1VO> users = repo.findUser1VOByOrderByNameAsc();
		System.out.println("query15 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query16")
	public String query16() {
		List<User1VO> users = repo.findUser1VOByOrderByNameDesc();
		System.out.println("query16 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query17")
	public String query17() {
		List<User1VO> users = repo.findUser1VOByAgeGreaterThanOrderByAgeDesc(23);
		System.out.println("query17 결과: " + users);
		return "redirect:/";
	}
	@GetMapping("/query18")
	public String query18() {
		int count = repo.countUser1VOByUid("a102");
		System.out.println("query18 결과: " + count);
		return "redirect:/";
	}
	@GetMapping("/query19")
	public String query19() {
		int count = repo.countUser1VOByName("김수한무");
		System.out.println("query19 결과: " + count);
		return "redirect:/";
	}
	@GetMapping("/query20")
	public String query20() {
		System.out.println("query20 결과: " + repo.selectUserUnderAge30());
		return "redirect:/";
	}
	
	@GetMapping("/user1/findUser")
	public void findUser() {}
	
	@PostMapping("/user1/findUser")
	public String findUser(String name, RedirectAttributes re) {
		re.addAttribute("name", name);
		return "redirect:/user1/findUserResult";
	}
	
	@GetMapping("/user1/findUserResult")
	public String findUserResult(Model model, @RequestParam(value="name") String name) {
		List<User1VO> users = repo.selectUserByName(name);
		System.out.println(name);
		System.out.println("query21 결과: " + repo.selectUserByName(name));
		model.addAttribute("users", users);
		
		return "/user1/findUserResult";
	}
	
	@GetMapping("/query21")
	public String query21() {
		System.out.println("query21 결과: " + repo.selectUserByName("rr"));
		return "redirect:/";
	}
}
