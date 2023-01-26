package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    public int insertArticle(ArticleVO vo){
        // 글 등록
        int result = dao.insertArticle(vo);

        // 파일 업로드
        FileVO fvo = uploadFile(vo);

        // 파일 board_file에 등록
        if(fvo != null)
            dao.insertFile(fvo);

        return result;
    }
    public ArticleVO selectArticle(int no){
        return dao.selectArticle(no);
    }
    public List<ArticleVO> selectArticles(String cate, int start){
        return dao.selectArticles(cate, start);
    }
    public int updateArticle(ArticleVO vo){
        return dao.updateArticle(vo);
    }
    public int deleteArticle(int no){
        return dao.deleteArticle(no);
    }

    // 페이징
    // 글 총 갯수(total)
    public int selectCountTotal(String cate){
        return dao.selectCountTotal(cate);
    }

    // 현재 페이지 번호
    public int getCurrentPage(String pg){
        int currentPage = 1;

        if(pg != null)
            currentPage = Integer.parseInt(pg);

        return currentPage;
    }

    // 페이지 시작값
    public int getLimitStart(int currentPage){
        return (currentPage - 1) * 10;
    }

    // 마지막 페이지 번호
    public int getLastPageNum(int total){
        int lastPageNum = 0;

        if(total % 10 == 0)
            lastPageNum = total / 10;
        else
            lastPageNum = total /10 + 1;

        return lastPageNum;
    }

    // 페이지 시작 번호
    public int getPageStartNum(int total, int start){
        return total - start;
    }

    // 페이지 그룹
    public int[] getPageGroup(int currentPage, int lastPageNum){
        int groupCurrent = (int) Math.ceil(currentPage/10.0);
        int groupStart = (groupCurrent - 1)* 10 + 1;
        int groupEnd = groupCurrent * 10;

        if(groupEnd > lastPageNum)
            groupEnd = lastPageNum;

        int[] groups = {groupStart, groupEnd};
        return groups;
    }

    // 파일 업로드

    // application.properties에서 설정한 파일 저장 경로 주입받기
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public FileVO uploadFile(ArticleVO vo){
        // 첨부 파일
        MultipartFile file = vo.getFname();
        FileVO fvo = null;

        if(!file.isEmpty()){
            // 시스템 경로
            String path = new File(uploadPath).getAbsolutePath();

            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString() + ext;

            // 파일 저장
            try {
                file.transferTo(new File(path, nName));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            fvo = new FileVO().builder().parent(vo.getNo()).oriName(oName).newName(nName).build();
        }
        return fvo;
    }

    // 파일 다운로드
    public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException{
        Path path = Paths.get(uploadPath + "/" + vo.getNewName());
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(vo.getOriName(), StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    // 파일 조회
    public FileVO selectFile(int fno){
        return dao.selectFile(fno);
    }

    // 파일 다운로드 카운터 증가
    public int updateFileDownload(int fno){
        return dao.updateFileDownload(fno);
    }
}

