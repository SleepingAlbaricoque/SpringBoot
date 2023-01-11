package kr.co.ch07.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User4Repo;
import kr.co.ch07.vo.User4VO;

@Service
public class User4Service {
	
	@Autowired
	private User4Repo repo;
	
	public void insertUser4(User4VO vo) {
		repo.save(vo);
	}
	public User4VO selectUser4(int seq) {
		return repo.findById(seq).get();
	}
	public List<User4VO> selectUser4s() {
		return repo.findAll();
	}
	public void updateUser4(User4VO vo) {
		repo.save(vo);
	}
	public void deleteUser4(int seq) {
		repo.deleteById(seq);
	}
}
