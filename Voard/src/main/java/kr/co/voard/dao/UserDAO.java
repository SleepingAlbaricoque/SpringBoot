package kr.co.voard.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.voard.vo.ArticleVO;
import kr.co.voard.vo.TermsVO;
import kr.co.voard.vo.UserVO;


@Mapper
@Repository
public interface UserDAO {
	
	public int insertUser(UserVO vo); // 영향받는 행의 개수를 return
	public TermsVO selectTerms();
	public void selectUser();
	public void selectUsers();
	public void updateUser();
	public void deleteUser();
	
	public void insertArticle(ArticleVO vo);
}
