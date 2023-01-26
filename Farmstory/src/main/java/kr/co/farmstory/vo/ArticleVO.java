package kr.co.farmstory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String title;
    private String content;
    private int file;
    private int hit;
    private String uid;
    private String regip;
    private String rdate;
    public String getRdate(){
        return rdate.substring(2, 10);
    }

    // list 출력을 위해 추가
    private String nick;

    // 파일 첨부를 위해 추가
    private MultipartFile fname;

    // 파일 다운로드를 위해 추가
    private FileVO fileVO;
}
