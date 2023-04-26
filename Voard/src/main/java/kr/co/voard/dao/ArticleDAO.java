package kr.co.voard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.voard.vo.ArticleVO;
import kr.co.voard.vo.FileVO;


@Mapper
@Repository
public interface ArticleDAO {
	
	public int insertArticle(ArticleVO vo); // vo가 매퍼의 쿼리문과 바인딩되어서 매퍼가 keyProperty로 no를 리턴하면 no값이 해당 vo에 담기게 된다
	public int insertFile(FileVO vo);
	public int selectCountTotal();
	public ArticleVO selectArticle(int no);
	public List<ArticleVO> selectArticles(int start);
	public FileVO selectFile(int fno);
	public int updateFileDownload(int fno);
	public int updateArticle(ArticleVO vo);
	public int deleteArticle(int no);
}
