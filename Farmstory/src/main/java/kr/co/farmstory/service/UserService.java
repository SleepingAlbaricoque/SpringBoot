package kr.co.farmstory.service;

import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JavaMailSender mailSender;

    public int insertUser(UserVO vo){
        vo.setPass(passwordEncoder.encode(vo.getPass2()));
        return dao.insertUser(vo);
    }
    public UserVO selectUser(String uid){
        return dao.selectUser(uid);
    }
    public List<UserVO> selectUsers(){
        return dao.selectUsers();
    }
    public int updateUser(UserVO vo){
        return dao.updateUser(vo);
    }
    public int deleteUser(String uid){
        return dao.deleteUser(uid);
    }

    // Print terms
    public TermsVO selectTerms(){
        return dao.selectTerms();
    }
    
    // checkUid
    public int countUser(String uid) {
    	return dao.countUser(uid);
    }
    
    // checkNick
    public int countUserByNick(String nick) {
    	return dao.countUserByNick(nick);
    }
    
    // email verification
    public int[] sendEmail(String email) {
    	int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
    	int result = 0;
    	
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom("tnqls0421@gmail.com");
    	message.setTo(email);
    	message.setText(String.valueOf(code));
    	message.setSubject("Farmstory Verification Code");
    	
    	try{
    		mailSender.send(message);
    		result = 1;
    	}catch(Exception e) {
    		System.out.println("Mail Not Sent");
    		e.printStackTrace();
    		result = 0;
    	}
    	
    	int[] report = {result, code};
    	return report;
    }
}
