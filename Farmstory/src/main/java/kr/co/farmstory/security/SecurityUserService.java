package kr.co.farmstory.security;

import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityUserService implements UserDetailsService{

	@Autowired
	private UserDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserVO user = dao.selectUser(username);

		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// 세션에 저장될 사용자 객체 생성
		UserDetails userDts = MyUserDetails.builder().user(user).build();
		
		return userDts;
	}

	
}
