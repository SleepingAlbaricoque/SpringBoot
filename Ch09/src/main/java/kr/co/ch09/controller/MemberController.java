package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.MemberService;
import kr.co.ch09.vo.MemberVO;

@RestController
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/member")
	public List<MemberVO> list() {
		List<MemberVO> members = service.selectMembers();
		return members;
	}
	
	@GetMapping("/member/{uid}")
	public MemberVO list(@PathVariable("uid") String uid) {
		return service.selectMember(uid);
	}
	
	@PostMapping("/member")
	public void register(MemberVO vo) {
		service.insertMember(vo);
	}
	
	@PutMapping("/member")
	public void update(MemberVO vo) {
		service.updateMember(vo);
	}
	
	@DeleteMapping("/member/{uid}")
	public void delete(@PathVariable("uid") String uid) {
		service.deleteMember(uid);
	}
}
