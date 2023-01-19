package kr.co.sboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	public int insertArticle(ArticleVO vo) {
		
		int result = 0;
		MultipartFile file = vo.getFname();
		
		if(file.isEmpty()) { // 첨부파일 무
			vo.setFile(0);
			result = dao.insertArticle(vo);
			
		}else { // 첨부파일 유
			vo.setFile(1);
			result = dao.insertArticle(vo);
			
			log.info("file: " + vo.getFile());
			log.info("no: " + vo.getNo());
			
			// 파일 업로드 to file file
			FileVO fileVO = new FileVO();
			fileVO.setParent(vo.getNo());
			fileUpload(file, fileVO); // vo.getFname()이 업로드 하려는 파일
			
			// 파일 등록 to board_file
				// 등록하려면 글의 no를 parent값으로 가져와야 함 => insertArticle()이 no값을 리턴하도록 설정하기
			dao.insertFile(fileVO);
		}
		
		return result;
	}
	public ArticleVO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleVO> selectArticles(){
		return dao.selectArticles();
	}
	public int updateArticle(ArticleVO vo) {
		return dao.updateArticle(vo);
	}
	public int deleteArticle(int no) {
		return dao.deleteArticle(no);
	}
	
	// 파일 업로드
	
	// application.properties에서 경로 주입받기
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	public void fileUpload(MultipartFile file, FileVO fileVO) {
		// application.properties에는 프로젝트 경로; 여기서는 시스템 경로 적어줘야 함
		String path = new File(uploadPath).getAbsolutePath(); // uploadPath의 절대 경로 찾기
		
		// 새 파일명 생성
		String oName = file.getOriginalFilename();
		String ext = oName.substring(oName.lastIndexOf("."));
		String nName = UUID.randomUUID().toString() + ext;
		
		// 파일 저장
		fileVO.setOriName(oName);
		fileVO.setNewName(nName);
		
		try {
			file.transferTo(new File(path, nName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
}
