package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate, @RequestParam (value="pg", defaultValue = "1") String pg){
        // 페이징 처리
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        int total = service.selectCountTotal(cate);
        int lastPageNum = service.getLastPageNum(total);
        int startPageNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);

        List<ArticleVO> articles = service.selectArticles(cate, start);
        //log.info("currentPage" + currentPage);
        //log.info("lastPageNum" + groups[1]);

        model.addAttribute("groups", groups);
        model.addAttribute("articles", articles);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("startPageNum", startPageNum);
        return "board/list";
    }

    @GetMapping("board/view")
    public String view(Model model, String group, String cate, int no){
        ArticleVO article = service.selectArticle(no);
        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        //log.info("fno" + article.getFileVO().getFno());
        return "board/view";
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(int fno) throws IOException {
        // 파일 조회
        FileVO vo = service.selectFile(fno);

        // 파일 다운로드 카운터 증가
        service.updateFileDownload(fno);

        // 파일 다운로드
        return service.fileDownload(vo);
    }

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/write";
    }

    @PostMapping("board/write")
    public String write(ArticleVO vo, HttpServletRequest req, String group, String cate, RedirectAttributes re){
        vo.setRegip(req.getRemoteAddr());
        service.insertArticle(vo);
        re.addAttribute("group", group);
        re.addAttribute("cate", cate);
        return "redirect:/board/list";
    }

    @GetMapping("board/modify")
    public String modify(Model model, String group, String cate, int no){
        model.addAttribute("no", no);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        ArticleVO article = service.selectArticle(no);
        model.addAttribute("article", article);
        return "board/modify";
    }

    @PostMapping("board/modify")
    public String modify(ArticleVO vo, String group, String cate, RedirectAttributes re){
        int fileCheck = service.checkArticleFile(vo.getNo());
        service.updateArticle(vo);
        re.addAttribute("group", group);
        re.addAttribute("cate", cate);
        return "redirect:/board/list";
    }

    @GetMapping("board/delete")
    public String delete(String group, String cate, int no, RedirectAttributes re){
        service.deleteArticle(no);
        re.addAttribute("group", group);
        re.addAttribute("cate", cate);
        return "redirect:/board/list";
    }
}
