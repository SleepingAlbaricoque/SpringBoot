package kr.co.farmstory.dao;

import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDAO {
    public int insertUser(UserVO vo);
    public UserVO selectUser(String uid);
    public List<UserVO> selectUsers();
    public int updateUser(UserVO vo);
    public int deleteUser(String uid);

    // Print terms
    public TermsVO selectTerms();
    
    // checkUid
    public int countUser(String uid);
    
    // checkNick
    public int countUserByNick(String nick);
}
