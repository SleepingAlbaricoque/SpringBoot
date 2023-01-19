package kr.co.sboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import kr.co.ch08.service.User2Service;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 인가(접근 권한) 설정
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/list").hasAnyRole("2", "3", "4", "5");
		http.authorizeRequests().antMatchers("/write").hasAnyRole("3", "4", "5");
		http.authorizeRequests().antMatchers("/view").hasAnyRole("3", "4", "5");
		http.authorizeRequests().antMatchers("/modify").hasAnyRole("3", "4", "5");
		
		// 사이트 위조 방지 설정
		http.csrf().disable();
		
		
		// 로그인 설정
		http.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/list")
		.failureUrl("/user/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		// 로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/user/login?success=200");
		
	}
	
	@Autowired
	private SecurityUserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Security 사용자에 대한 권한 설정
		// auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
		// auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		
		// 로그인 인증 처리 서비스, 암호화 방식 설정; Spring Security는 패스워드 강제로 암호화함
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
