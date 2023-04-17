package kr.co.voard.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.co.voard.security.MyUserDetails;
import kr.co.voard.security.SecurityUserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private SecurityUserService securityUserService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = jwtUtil.resolveToken(request);
		
		if(token!= null && jwtUtil.validateToken(token)) {
			log.info("auth2");
			String uid = jwtUtil.getUsernameFromToken(token);
			MyUserDetails myUserDetails = securityUserService.loadUserByUsername(uid);

			// security 인증처리
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(myUserDetails,
																										null,
																										myUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// 다음 필터로 이동
		filterChain.doFilter(request, response);
	}
	

}
