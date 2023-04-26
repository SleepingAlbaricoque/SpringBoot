package kr.co.voard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.voard.dao.UserDAO;
import kr.co.voard.repository.UserRepo;
import kr.co.voard.vo.ArticleVO;
import kr.co.voard.vo.TermsVO;
import kr.co.voard.vo.UserVO;


@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	public int insertUser(UserVO vo) {
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		int result = dao.insertUser(vo);
		
		return result;
	}
	
	public int countUid(String uid) {
		return repo.countByUid(uid);
	}
	
	public void insertArticle(ArticleVO vo) {
		dao.insertArticle(vo);
	}
}
