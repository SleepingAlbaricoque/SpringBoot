package kr.co.voard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.voard.repository.UserEntity;
import kr.co.voard.repository.UserRepo;


@Service
public class SecurityUserService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = repo.findById(username).get();
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// 세션에 저장될 사용자 객체 생성
		MyUserDetails userDts = MyUserDetails.builder().user(user).build();
		
		return userDts;
	}

	
}
